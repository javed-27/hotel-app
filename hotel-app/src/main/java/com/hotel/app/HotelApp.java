package com.hotel.app;


import com.hotel.app.controller.DebugService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HotelApp {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(HotelApp.class);
        ConfigurableApplicationContext ctx = app.run(args);
        app.setBannerMode(Banner.Mode.OFF);
        DebugService bean = ctx.getBean(DebugService.class);
        bean.printMongoInfo();
    }

}
