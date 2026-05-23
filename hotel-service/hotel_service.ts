// @ts-ignore
import * as hotelRepo from "./hotel_repo.ts";

export function searchHotels(city = "") {
    return hotelRepo.getHotelsByCity(city);
}

export async function book(bookingRequest: { hotelId: number; rooms: number }, userId) {
    console.log({bookingRequest, userId})
    const hotelId = bookingRequest.hotelId;
    const rooms = bookingRequest.rooms;
    const hotel = await hotelRepo.getHotelsByHotelId(hotelId);
    const updatedRooms = hotel!.availableRooms - rooms;
    await hotelRepo.book(bookingRequest, userId)
    await hotelRepo.updateAvailableRoomsByHotelId(hotelId, updatedRooms);
}
