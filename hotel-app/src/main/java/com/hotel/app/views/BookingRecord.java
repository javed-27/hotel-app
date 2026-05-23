package com.hotel.app.views;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record BookingRecord(@Id int bookingId, String userId, int hotelId, int totalRooms) {
}
