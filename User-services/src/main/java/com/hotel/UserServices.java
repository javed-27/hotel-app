package com.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServices {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(new Class[]{UserServices.class});
        app.run(args);
    }
}
