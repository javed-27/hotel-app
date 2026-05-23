package com.hotel.app.services;

import com.hotel.app.models.User;
import com.hotel.app.repository.Users;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Id
    private int userId;
    private final Users userRepo;

    public UserService(Users userRepo) {
        this.userRepo = userRepo;
    }

    public String createUser(String username, String password) {
        User user = new User(username, password ,this.generateId());

        User save = userRepo.save(user);
        return this.getUserId(username, password);
    }

    public String  getUserId(String username, String password){
        User user = userRepo.getUserId(username, password);
        return user.getuserId();
    }

    public String generateId(){
        return  UUID.randomUUID().toString();
    }
}
