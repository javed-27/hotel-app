package com.hotel.controllers;

import com.hotel.services.UserService;
import com.hotel.utils.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class AuthController {
    private final UserService users;

    public AuthController(UserService users) {
        this.users = users;
    }

    @PostMapping({"/api/users/register"})
    public String signup(@RequestBody Credentials credentials) {
        String userId = this.users.createUser(credentials.username(), credentials.password());
        return JwtUtil.generateToken(userId);
    }

    @PostMapping({"/api/users/login"})
    public String login(@RequestBody Credentials credentials) {
        String userId = this.users.getUserId(credentials.username(), credentials.password());
        return JwtUtil.generateToken(userId);
    }
}
