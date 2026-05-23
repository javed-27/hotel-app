package com.hotel.services;

import com.hotel.models.User;
import com.hotel.repository.Users;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Id
    private int userId;
    private final Users userRepo;

    public UserService(Users userRepo) {
        this.userRepo = userRepo;
    }

    public String createUser(String username, String password) {
        User user = new User(username, password, this.generateId());
        User save = (User)this.userRepo.save(user);
        return this.getUserId(username, password);
    }

    public String getUserId(String username, String password) {
        User user = this.userRepo.getUserId(username, password);
        return user.getuserId();
    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
