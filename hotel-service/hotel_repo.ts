// @ts-ignore
import { MongoClient } from "mongodb";

const mongoUrl = "mongodb://mongodb:27017/hoteldb";

const client = new MongoClient(mongoUrl);
// @ts-ignore
await client.connect();

const db = client.db("hoteldb");

export const hotelRepository = db.collection("hotels");
// console.log("inserting ..")
// await hotelRepository.insertOne({
//   _id: 1,
//   name: "Saxon",
//   city: "New York",
//   availableRooms: 10,
// });

export async function getHotelsByCity(city: string) {
  const result = await hotelRepository.find({ city }).toArray();
  console.log(result);
  return result.map(({ name, availableRooms, city, _id }) => ({
    name,
    availableRooms,
    city,
    id: _id,
  }));
}

export async function book(bookingRequest: any, userId: string) {
  const hotelId = bookingRequest.hotelId;
  const rooms = bookingRequest.rooms;

  const hotel = await getHotelsByHotelId(hotelId);

  if (!hotel) {
    throw new Error("Hotel not found");
  }

  if (rooms > hotel.availableRooms) {
    throw new Error(
      `Only ${hotel.availableRooms} rooms are available`,
    );
  }

  const bookingRecord = {
    bookingId: Date.now(), // simple unique id
    userId,
    hotelId,
    rooms,
  };

  const bookingCollection = db.collection("booking");
  await bookingCollection.insertOne(bookingRecord);

  const updatedRooms = hotel.availableRooms - rooms;

  await updateAvailableRoomsByHotelId(
    hotelId,
    updatedRooms,
  );

  return bookingRecord;
}

export async function getHotelsByHotelId(hotelId: number) {
  const hotel = await hotelRepository.findOne({ _id: hotelId });
  return hotel;
}

// @ts-ignore
export async function updateAvailableRoomsByHotelId(
  hotelId: number,
  updatedRooms: number,
) {
  await hotelRepository.updateOne(
    { _id: hotelId },
    { $set: { availableRooms: updatedRooms } },
  );
}
