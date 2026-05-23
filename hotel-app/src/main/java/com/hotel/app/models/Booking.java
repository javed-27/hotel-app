package com.hotel.app.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record Booking(int bookingId, int hotelId, int userId, int roomCount) {
}
