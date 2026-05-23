package com.hotel.app.repository;

import com.hotel.app.views.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelsRepository extends MongoRepository<Hotel,Integer> {
    List<Hotel> getHotelsByCity(String city);
    Hotel getHotelsByHotelId(int hotelId);

    @Query("{ 'hotelId' : ?0 }")
    @Update("{ '$set' : { 'availableRooms' : ?1 } }")
    void updateAvailableRoomsByHotelId(int hotelId, double availableRooms);

}
