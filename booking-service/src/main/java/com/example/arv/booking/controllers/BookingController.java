package com.example.arv.booking.controllers;

import com.example.arv.booking.entities.Booking;
import com.example.arv.booking.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {


    private final static Logger LOG = LoggerFactory.getLogger(BookingController.class);
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking bookingRequest) {
        //TODO: Validator and global exception handling
        LOG.info("createBooking for showId {}, userId {}, bookingQuoteId {} ", bookingRequest.getShowId(), bookingRequest.getUserId(), bookingRequest.getBookingQuote().getId());
        Booking booking = bookingService.createBooking(bookingRequest);
        return ResponseEntity.created(URI.create("/api/v1/bookings" + booking.getId())).body(booking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking != null) {
            return ResponseEntity.ok(booking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Other endpoints like updating and deleting bookings
}
