package com.example.arv.booking.entities;

import com.example.arv.booking.constants.BookingQuoteStatus;
import com.example.arv.booking.constants.BookingStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingQuote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long showId;

    private Long userId;

    private int numberOfTickets;
    private BigDecimal totalPrice;
    private BigDecimal totalPriceAfterDiscount;

    @Enumerated(EnumType.STRING)
    private BookingQuoteStatus status;

}

