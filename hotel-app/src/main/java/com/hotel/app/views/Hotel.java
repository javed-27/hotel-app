package com.hotel.app.views;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record Hotel(@Id  int hotelId , String name, int availableRooms,  String city) {
}
