package com.hotel.app.services;

import com.hotel.app.exceptions.InvalidRequestException;
import com.hotel.app.views.Hotel;
import com.hotel.app.repository.HotelsRepository;
import com.hotel.app.views.BookingRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final HotelsRepository hotelRepo;
    private final BookingService bookingService;
    private int id;
    private final String msg = "only %d room are available";

    public HotelService(HotelsRepository hotelsRepository, BookingService bookingService) {
        this.hotelRepo = hotelsRepository;
        this.bookingService = bookingService;
        this.id = 0;
    }

    @PostConstruct
    private void addHotels() {
        Hotel hotel1 = new Hotel(this.NextId(), "Continental", 10, "New York");
        Hotel hotel2 = new Hotel(this.NextId(), "Tower", 5, "Prayagraj");
        Hotel hotel3 = new Hotel(this.NextId(), "Sackson", 3, "New York");
        hotelRepo.save(hotel1);
        hotelRepo.save(hotel2);
        hotelRepo.save(hotel3);
    }

    private int NextId() {
        return ++this.id;
    }

    public List<Hotel> searchHotels(String city) {
        return hotelRepo.getHotelsByCity(city);
    }

    public void book(BookingRequest bookingRequest,String userId) throws InvalidRequestException {
        int hotelId = bookingRequest.hotelId();
        int rooms = bookingRequest.rooms();
        Hotel hotel = hotelRepo.getHotelsByHotelId(hotelId);
        if (rooms > hotel.availableRooms()) {
            throw new InvalidRequestException(msg.formatted(hotel.availableRooms()));
        }
        this.bookingService.book(bookingRequest,userId);//appi call
        int updatedRooms = hotel.availableRooms() - rooms;
        this.hotelRepo.updateAvailableRoomsByHotelId(hotelId, updatedRooms);
    }

}
