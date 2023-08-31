package com.example.arv.booking.model;

import com.example.arv.booking.constants.ShowStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Show {
    private Long id;

    private Movie movie;

    private Theater theater;

    private LocalDateTime showTime;

    private ShowStatus status;

    private BigDecimal pricePerTicket;

    public Show(Movie movie, Theater theater, LocalDateTime showTime, BigDecimal pricePerTicket) {
        this.movie = movie;
        this.theater = theater;
        this.showTime = showTime;
        this.status = ShowStatus.ACTIVE;
        this.pricePerTicket = pricePerTicket;
    }

}


