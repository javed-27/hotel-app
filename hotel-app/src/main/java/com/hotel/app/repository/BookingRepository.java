package com.hotel.app.repository;

import com.hotel.app.views.BookingRecord;
import com.hotel.app.views.BookingRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<BookingRecord, String> {
    List<BookingRecord> getBookingsByUserId(String userId);
}
