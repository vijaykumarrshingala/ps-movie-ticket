package com.example.arv.booking.repositories;

import com.example.arv.booking.entities.BookingQuote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingQuoteRepository extends JpaRepository<BookingQuote, Long> {
}
