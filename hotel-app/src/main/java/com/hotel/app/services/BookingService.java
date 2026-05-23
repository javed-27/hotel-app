package com.hotel.app.services;

import com.hotel.app.repository.BookingRepository;
import com.hotel.app.views.BookingRecord;
import com.hotel.app.views.BookingRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BookingService {

    private final HashMap<String, BookingRecord> bookingDetails;
    private final BookingRepository bookingRepository;
    private int bookingId;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingDetails = new HashMap<String, BookingRecord>();
        this.bookingId = 0;
    }

    public BookingRecord book(BookingRequest bookingRequest,String userId) {
        System.out.println(userId + "at the book");
        BookingRecord bookingRecord = new BookingRecord(this.nextId(),
                userId,
                bookingRequest.hotelId(),
                bookingRequest.rooms());

        this.bookingRepository.save(bookingRecord);
        return bookingRecord;
    }

    public List<BookingRecord> getBookingsByUSerId(String userId) {
        return bookingRepository.getBookingsByUserId(userId);
    }

    private int nextId() {
        return ++this.bookingId;
    }

    public String buildPdf(List<BookingRecord> bookings) {
        bookings.forEach(booking -> {

        });
        return "";
    }
}
