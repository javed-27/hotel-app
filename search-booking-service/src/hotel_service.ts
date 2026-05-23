// @ts-ignore:
import * as hotelRepo from "./hotel_repo.ts";

export function searchHotels(city: string) {
  return hotelRepo.getHotelsByCity(city);
}

interface BookingRequest {
  hotel_id: number;
  rooms: number;
}

export async function book(bookingReq: BookingRequest, userId: string) {
  console.log({ bookingReq });

  const { hotel_id: hotelId, rooms } = bookingReq;

  const hotel = await hotelRepo.getHotelsByHotelId(hotelId);

  const remainingRooms = hotel!.availableRooms - rooms;
  await hotelRepo.book(bookingReq, userId);
  await hotelRepo.updateAvailableRoomsByHotelId(hotelId, remainingRooms);
}
