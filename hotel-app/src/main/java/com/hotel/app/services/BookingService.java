package com.hotel.app.services;

import com.hotel.app.repository.BookingRepository;
import com.hotel.app.views.BookingRecord;
import com.hotel.app.views.BookingRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<BookingRecord> getBookingsByUSerId(String userId) {
        return bookingRepository.getBookingsByUserId(userId);
    }

    public String buildPdf(List<BookingRecord> bookings) {
        return "";
    }
}
