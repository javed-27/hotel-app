// @ts-ignore:
import { Hono } from "hono";
import { logger } from "hono/logger";
// @ts-ignore:
import * as hotelService from "../src/hotel_service.ts";

export const createApp = () => {
  const app = new Hono();

  app.use(logger());

  app.post("/api/bookings", async (c) => {
    const token = c.req.header("Authorization");
    const bookingRequest = await c.req.json();
    await hotelService.book(bookingRequest, token!);
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
