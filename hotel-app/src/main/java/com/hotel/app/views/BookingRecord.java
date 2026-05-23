package com.hotel.app.views;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("booking")
public record BookingRecord(Number bookingId, String userId, Number hotelId, Number rooms) {
}

/*
*
* bookingId: 1779527880677,
    userId: 'a0666bb1-cde6-4bd7-9be3-c05ad5858b60',
    hotelId: 1,
    rooms: 1*/