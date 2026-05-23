package com.hotel.app.repository;

import com.hotel.app.views.BookingRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Booking extends MongoRepository<BookingRecord, String> {
    @Query("{ 'userId' : ?0 }")
    List<BookingRecord> findBookingsByUserId(String userId);
}
