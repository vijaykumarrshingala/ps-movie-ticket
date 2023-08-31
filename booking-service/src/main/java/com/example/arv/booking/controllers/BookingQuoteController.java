package com.example.arv.booking.controllers;


import com.example.arv.booking.constants.BookingQuoteStatus;
import com.example.arv.booking.entities.BookingQuote;
import com.example.arv.booking.httpclient.ShowFeignClient;
import com.example.arv.booking.model.Show;
import com.example.arv.booking.repositories.BookingQuoteRepository;
import com.example.arv.booking.services.BookingQuoteService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bookings/booking-quotes")
@RequiredArgsConstructor
public class BookingQuoteController {

    private final static Logger LOG = LoggerFactory.getLogger(BookingQuoteController.class);
    private final BookingQuoteService bookingQuoteService;

    @PostMapping
    public ResponseEntity<BookingQuote> createBookingQuote(@RequestBody BookingQuote bookingQuote) {
        LOG.info("createBookingQuote.....");
        BookingQuote resQuote = bookingQuoteService.createBookingQuote(bookingQuote);
        if(resQuote.getId() == 0) {
            LOG.error("Looks like showFeignClient's getShowById having issue for userId {}, showId {}", bookingQuote.getUserId(), bookingQuote.getUserId());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(resQuote);
        }
        return ResponseEntity.created(URI.create("/api/v1/bookings/booking-quotes/"+resQuote.getId())).body(resQuote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingQuote> getBookingQuote(@PathVariable Long id){
        LOG.info("getBookingQuote for id {}", id);
        Optional<BookingQuote> bookingQuote = bookingQuoteService.findById(id);
        if (bookingQuote.isPresent()) {
            LOG.info("bookingQuote available for id {}", id);
            return ResponseEntity.ok(bookingQuote.get());
        } else {
            LOG.error("Invalid bookingQuote id {}", id);
            return ResponseEntity.badRequest().body(new BookingQuote());
        }
    }
}

