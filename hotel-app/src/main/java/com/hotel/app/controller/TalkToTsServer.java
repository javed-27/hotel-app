package com.hotel.app.controller;

import com.hotel.app.models.HotelBooking;
import com.hotel.app.services.BookingService;
import com.hotel.app.services.HotelService;
import com.hotel.app.views.BookingRecord;
import com.hotel.app.views.BookingRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TalkToTsServer {
    private final HotelService hotelService;
    private final BookingService bookingService;

    public TalkToTsServer(HotelService hotelService, BookingService bookingService) {
        this.hotelService = hotelService;
        this.bookingService = bookingService;
    }

    @PostMapping("/talk")
    public String talk(@RequestBody BookingRequest br, @AuthenticationPrincipal UserDetails userDetails) {
        String userId = userDetails.getUsername();
        BookingRecord book = bookingService.book(br, userId);
        System.out.println("Here in talk");
        return "Hello we got body from" + book;
    }

}
