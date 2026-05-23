package com.hotel.app.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("here in loadUserByName");

        System.out.println("---".repeat(10));
        System.out.println(username);
        System.out.println("---".repeat(10));

        return User.builder().username(username).authorities("USER").build();
    }
}
