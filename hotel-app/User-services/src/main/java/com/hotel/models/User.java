package com.hotel.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    @Id
    private final String userId;
    final String username;
    final String password;

    public String getuserId() {
        return this.userId;
    }

    public User(String username, String password, String userId) {
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public String toString() {
        return "User{username='" + this.username + "', password='" + this.password + "'}";
    }
}
