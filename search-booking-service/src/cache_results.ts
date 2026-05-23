import { client } from "./redis_services.ts";

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
