import { createClient } from "redis";

const client = createClient({ url: Deno.env.get("CACHE_URL") });

await client.connect();
type HotelType = {
  name: string;
  rooms: number;
  city: string;
  id: string;
};

export const getHotels = async (city: string) => {
  return await client.get(city);
};

export const setHotels = async (city: string, result: HotelType[]) => {
  return await client.set(city, JSON.stringify(result));
};
