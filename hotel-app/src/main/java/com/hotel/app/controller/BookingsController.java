package com.hotel.app.controller;


import com.hotel.app.services.BookingService;
import com.hotel.app.views.BookingRecord;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingsController {
    private final BookingService bookingService;

    public BookingsController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/api/bookings/")
    public ResponseEntity<List<BookingRecord>> getBookings(Authentication authentication) {
        System.out.println(authentication.getName());

        List<BookingRecord> bookingsByUserId = bookingService.getBookingsByUserId(authentication.getName());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(bookingsByUserId);
    }
}
