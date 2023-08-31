package com.example.arv.booking.services;


import com.example.arv.booking.constants.BookingQuoteStatus;
import com.example.arv.booking.controllers.BookingQuoteController;
import com.example.arv.booking.entities.BookingQuote;
import com.example.arv.booking.httpclient.ShowFeignClient;
import com.example.arv.booking.model.Show;
import com.example.arv.booking.repositories.BookingQuoteRepository;
import com.example.arv.booking.utils.LocalDateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingQuoteService {

    private final static Logger LOG = LoggerFactory.getLogger(BookingQuoteService.class);

    private final BookingQuoteRepository bookingQuoteRepository;
    private final ShowFeignClient showFeignClient;

    public BookingQuote createBookingQuote(BookingQuote bookingQuote) {
        LOG.info("createBookingQuote.....");
        BookingQuote createdQuote = new BookingQuote();
        // Fetch the corresponding Show entity based on showId
        Show show = showFeignClient.getShowById(bookingQuote.getShowId());
        // Calculate the total price based on movie price and number of tickets
        if(show.getId() != 0) {
            BigDecimal totalPrice = show.getPricePerTicket().multiply(BigDecimal.valueOf(bookingQuote.getNumberOfTickets()));
            bookingQuote.setTotalPrice(totalPrice);
            BigDecimal discount = applyDiscount(bookingQuote, show);
            bookingQuote.setTotalPriceAfterDiscount(discount);
            bookingQuote.setStatus(BookingQuoteStatus.CREATED);
            // Save the booking quote
            createdQuote = bookingQuoteRepository.save(bookingQuote);
        }

        return createdQuote;
    }

    public Optional<BookingQuote> findById(Long id) {
        return bookingQuoteRepository.findById(id);
    }

    public BigDecimal applyDiscount(BookingQuote bookingQuote, Show show) {
        DiscountStrategy discountStrategy = determineDiscountStrategy(show);
        BigDecimal totalPrice = bookingQuote.getTotalPrice();
        if (discountStrategy != null) {
            totalPrice = discountStrategy.applyDiscount(totalPrice);
        }
        return totalPrice;
    }

    public DiscountStrategy determineDiscountStrategy(Show show) {
        // Get user information, show details, etc.
        //TODO : Can put more condition based on business needs and also shift this method to dedicated DiscountStrategyFactory class as well
        if (LocalDateTimeUtils.isAfternoon(show.getShowTime())) {
            return new PercentageDiscountStrategy(20); // 20% discount for premium users
        }
        // Apply no discount if no specific criteria match
        return new NoDiscountStrategy();
    }







}
