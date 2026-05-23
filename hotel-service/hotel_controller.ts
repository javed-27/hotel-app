// @ts-ignore
// checking push
import { Hono } from "hono";
import { logger } from "hono/logger";
// @ts-ignore
import * as hotelBooking from "./hotel_service.ts";
const app = new Hono();
console.log("running...");
app.use(logger());

app.post("/api/bookings", async (c) => {
  const token = c.req.header("Authorization");
  const bookingRequest = await c.req.json();
  await hotelBooking.book(bookingRequest, token);
  return c.text("Successful booking");
});

app.get("/api/search/hotels", async (c) => {
  const city = c.req.query("city");
  const result = await hotelBooking.searchHotels(city);
  return c.json(result);
});

app.get("/", (c) => c.json("hello"));
export default app;
