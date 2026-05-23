import { Hono } from "hono";
import { logger } from "hono/logger";
import * as hotelService from "../src/hotel_service.ts";
import { verifyJWT } from "./jwt.ts";

export const createApp = () => {
  const app = new Hono();

  app.use(logger());

  app.post("/api/bookings", async (c) => {
    const [_bearer, token] = c.req.header("Authorization")!.split(" ");
    const bookingRequest = await c.req.json();
    const userId = await verifyJWT(token);

    await hotelService.book(bookingRequest, userId?.sub!);

    return c.text("Successful booking");
  });

  app.get("/api/search/hotels", async (c) => {
    const city = c.req.query("city");
    const result = await hotelService.searchHotels(city!);
    return c.json(result);
  });

  app.get("/", (c) => c.json("hello"));

  return app;
};
