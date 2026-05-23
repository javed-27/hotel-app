// @ts-ignore:
import * as hotelRepo from "./hotel_repo.ts";

export function searchHotels(city: string) {
  return hotelRepo.getHotelsByCity(city);
}

interface BookingRequest {
  hotelId: number;
  rooms: number;
}

export async function book(
  bookingRequest: BookingRequest,
  userId: string,
) {
  const bookingDetails = { bookingRequest, userId };
  console.log(bookingDetails);
  const hotelId = bookingRequest.hotelId;
  const rooms = bookingRequest.rooms;
  const hotel = await hotelRepo.getHotelsByHotelId(hotelId);
  const updatedRooms = hotel!.availableRooms - rooms;
  await hotelRepo.book(bookingRequest, userId);
  await hotelRepo.updateAvailableRoomsByHotelId(hotelId, updatedRooms);
}
