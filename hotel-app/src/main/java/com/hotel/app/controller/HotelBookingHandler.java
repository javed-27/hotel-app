package com.hotel.app.controller;

import com.hotel.app.services.BookingService;
import com.hotel.app.views.BookingRecord;
import com.hotel.app.views.BookingRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Component
@RestController
public class HotelBookingHandler {
    private final BookingService bookingService;

    public HotelBookingHandler(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/api/bookings")
    public ResponseEntity<List<BookingRecord>> getBookings(
            @AuthenticationPrincipal UserDetails userDetails
    ) {

        String userId = userDetails.getUsername();

        System.out.println(userId);
        System.out.println(userId + "at the list of bookings");

        List<BookingRecord> bookings =
                bookingService.getBookingsByUSerId(userId);

        return ResponseEntity.ok(bookings);
    }
}

