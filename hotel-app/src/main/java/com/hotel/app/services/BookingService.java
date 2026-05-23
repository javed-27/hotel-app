package com.hotel.app.services;

import com.hotel.app.repository.BookingRepository;
import com.hotel.app.views.BookingRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<BookingRecord> getBookingsByUserId(String userId) {
        System.out.println("before calling anything ");
        System.out.println("|||".repeat(10));
        List<BookingRecord> bookingsByUserId = bookingRepository.findByUserId(userId);
        return bookingsByUserId;
    }

    public byte[] buildPdf(String key) {

        return "Some reciept we got here".getBytes();
    }
}
