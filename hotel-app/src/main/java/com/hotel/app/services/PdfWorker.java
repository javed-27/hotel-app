package com.hotel.app.services;

import com.hotel.app.models.PdfJob;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;

@Service
public class PdfWorker {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookingService bookingService;

    @PostConstruct
    public void startWorker() {

        new Thread(() -> {

            while (true) {

                try {

                    // BRPOP from Redis queue
                    String json = redisTemplate.opsForList()
                            .rightPop("pdf:", Duration.ofSeconds(0));

                    System.out.println("Got from queue: " + json);

                    // convert JSON -> Java object
                    PdfJob job = objectMapper.readValue(json, PdfJob.class);

                    // generate pdf
                    byte[] pdfBytes =
                            bookingService.buildPdf(job.getUserId());

                    // save pdf
                    savePdf(job.getUserId(), pdfBytes);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    private void savePdf(String userId, byte[] pdfBytes) {

        System.out.println("Saving PDF for user: " + userId);

        // DB save logic here
    }
}