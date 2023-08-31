package com.example.arv.movie.catalog.entities;

import com.example.arv.movie.catalog.constants.ShowStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    private LocalDateTime showTime;

    @Enumerated(EnumType.STRING)
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


