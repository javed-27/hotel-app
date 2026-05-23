package com.hotel.app.controller;

import com.hotel.app.services.UserService;
import com.hotel.app.utils.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    private final UserService users;

    public AuthController(UserService users) {
        this.users = users;
    }

    @PostMapping("/api/users/register")
    public String signup(@RequestBody Credentials credentials) {
        String userId = users.createUser(credentials.username(), credentials.password());
        return JwtUtil.generateToken(userId);
    }

    @PostMapping("/api/users/login")
    public String login(@RequestBody Credentials credentials) {
        String userId = users.getUserId(credentials.username() , credentials.password());

        return JwtUtil.generateToken(userId);
    }

}
