import { MongoClient } from "mongodb";
import { HOTELS } from "./HOTELS.ts";

const mongoUrl = Deno.env.get("MONGO_URI");
const client = new MongoClient(mongoUrl!);

await client.connect();
const db = client.db("hoteldb");
export const hotelRepository = db.collection("hotels");

(async (x) => {
  if (x) return;

  for await (const hotel of HOTELS) {
    // @ts-ignore:
    await hotelRepository.insertOne(hotel);
  }
})(true);

export async function getHotelsByCity(city: string) {
  const result = await hotelRepository.find({ city }).toArray();

  return result.map(({ name, availableRooms, city, _id }) => ({
    name,
    room: availableRooms,
    city,
    id: _id,
  }));
}

interface BookingRequest {
  hotel_id: number;
  rooms: number;
}

export async function book(bookingRequest: BookingRequest, userId: string) {
  const hotelId = bookingRequest.hotel_id;
  const rooms = bookingRequest.rooms;

  const hotel = await getHotelsByHotelId(hotelId);

  if (hotel === null) {
    throw new Error("Hotel not found");
  }

  if (rooms > hotel.availableRooms) {
    throw new Error(`Only ${hotel.availableRooms} rooms are available`);
  }

  const bookingRecord = {
    bookingId: Date.now(),
    userId,
    hotelId,
    rooms,
  };

  const bookingCollection = db.collection("booking");
  await bookingCollection.insertOne(bookingRecord);

  const updatedRooms = hotel.availableRooms - rooms;

  await updateAvailableRoomsByHotelId(hotelId, updatedRooms);

  return bookingRecord;
}

export async function getHotelsByHotelId(hotelId: number) {
  // @ts-ignore:
  return await hotelRepository.findOne({ _id: hotelId });
}

export async function updateAvailableRoomsByHotelId(
  hotelId: number,
  roomCount: number,
) {
  await hotelRepository.updateOne(
    // @ts-ignore:
    { _id: hotelId },
    { $set: { availableRooms: roomCount } },
  );
}
