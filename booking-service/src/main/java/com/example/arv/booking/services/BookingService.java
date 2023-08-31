package com.example.arv.booking.services;

import com.example.arv.booking.constants.BookingQuoteStatus;
import com.example.arv.booking.constants.BookingStatus;
import com.example.arv.booking.entities.Booking;
import com.example.arv.booking.entities.BookingQuote;
import com.example.arv.booking.repositories.BookingQuoteRepository;
import com.example.arv.booking.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingQuoteRepository bookingQuoteRepository;
    //private final PaymentServiceFeignClient paymentServiceFeignClient;

    public Booking createBooking(Booking bookingRequest) {

        //TODO : Custom exception and global exception handler

        BookingQuote bookingQuote = bookingQuoteRepository.findById(bookingRequest.getBookingQuote().getId()).orElseThrow(() -> new RuntimeException("BookingQuote not found"));


        Booking booking = Booking.builder()
                .bookingTime(LocalDateTime.now())
                .bookingQuote(bookingQuote)
                .totalPrice(bookingQuote.getTotalPriceAfterDiscount())
                .status(BookingStatus.PENDING)
                .userId(bookingRequest.getUserId())
                .showId(bookingRequest.getShowId())
                .build();
        booking = bookingRepository.save(booking);

        bookingQuote.setStatus(BookingQuoteStatus.ACCEPTED);
        bookingQuoteRepository.save(bookingQuote);

        //TODO : microservice component 'payment-service' call
        String paymentResponse = "OK";

        if(paymentResponse != null && paymentResponse.equals("OK")) {
            booking.setStatus(BookingStatus.BOOKED);
            booking = bookingRepository.save(booking);
            System.out.println("Ticket booked for bookingId : "+booking.getId());
        } else {
            /**TODO : warning and scheduler should pick such pending  booking and check with payment service about status and updated
            booking status accordingly **/
        }
        return booking;
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

}

