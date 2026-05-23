import { Hono } from "hono";
import { logger } from "hono/logger";
import * as hotelService from "../src/hotel_service.ts";
import { verifyJWT } from "./jwt.ts";
import * as queue from "./worker_queue.ts";
import * as cache from "./cache_results.ts";

export const createApp = () => {
  const app = new Hono();

  app.use(logger());

  app.post("/api/bookings", async (c) => {
    const [_bearer, token] = c.req.header("Authorization")!.split(" ");
    const bookingRequest = await c.req.json();
    const userId = await verifyJWT(token);
    const bookingRecord = await hotelService.book(bookingRequest, userId?.sub!);

    queue.generatePdf(bookingRecord);

    return c.text("Successful booking " + JSON.stringify(bookingRecord));
  });

  type HotelType = {
    name: string;
    rooms: number;
    city: string;
    id: string;
  };

  app.get("/api/search/hotels", async (c) => {
    const city: string = c.req.query("city")!;

    const cachedResults = await cache.getHotels(city);
    if (cachedResults !== null) {
      console.log("Cache Hit");

      return c.json(JSON.parse(cachedResults));
    }

    //@ts-ignore:
    const result: HotelType[] = await hotelService.searchHotels(city!);
    cache.setHotels(city, result);
    console.log("Cache Miss");
    return c.json(result);
  });

  app.get("/", (c) => c.json("hello"));

  return app;
};
