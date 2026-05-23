package com.hotel.app.controller;

import com.hotel.app.services.BookingService;
import com.hotel.app.views.BookingRecord;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DownloadPdfController {
    private final BookingService bookingService;

    public DownloadPdfController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/api/bookings/{userId}/receiptreceipt.pdf")
    public ResponseEntity<String> downloadPdf(@PathVariable String userId) {
        List<BookingRecord> bookingsByUserId = bookingService.getBookingsByUSerId(userId);
        String pdf = bookingService.buildPdf(bookingsByUserId);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=receiptreceipt.pdf").body(pdf);
    }


}
