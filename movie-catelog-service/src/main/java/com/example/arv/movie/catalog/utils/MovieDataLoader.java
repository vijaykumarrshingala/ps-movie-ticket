package com.example.arv.movie.catalog.utils;

import com.example.arv.movie.catalog.entities.Movie;
import com.example.arv.movie.catalog.entities.Show;
import com.example.arv.movie.catalog.entities.Theater;
import com.example.arv.movie.catalog.repositories.MovieRepository;
import com.example.arv.movie.catalog.repositories.ShowRepository;
import com.example.arv.movie.catalog.repositories.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class MovieDataLoader implements CommandLineRunner {
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final ShowRepository showRepository;


    @Override
    public void run(String... args) throws Exception {
        // Create sample movies
        Movie movie1 = new Movie("Bahubali-2", "Action", LocalDate.parse("2023-08-01"));
        Movie movie2 = new Movie("Gadar 2", "Action", LocalDate.parse("2023-08-15"));
        Movie movie3 = new Movie("Dhamal", "Comedy", LocalDate.parse("2023-08-15"));
        Movie movie4 = new Movie("Dangal", "Sport drama", LocalDate.parse("2023-08-15"));

        movieRepository.saveAll(Arrays.asList(movie1, movie2));

        // Create sample theaters
        Theater theaterA = new Theater("PVR-Phonix", "VimanNagar, Pune");
        Theater theaterB = new Theater("PVR-Amanora", "Hadapsar, Pune");
        theaterRepository.saveAll(Arrays.asList(theaterA, theaterB));

        // Create sample shows
        Show show1 = new Show(movie1, theaterA, LocalDateTime.parse("2023-08-20T10:00:00"), new BigDecimal(100));
        Show show2 = new Show(movie1, theaterA, LocalDateTime.parse("2023-08-20T14:00:00"), new BigDecimal(110));

        Show show3 = new Show(movie2, theaterB, LocalDateTime.parse("2023-08-21T10:00:00"), new BigDecimal(100));
        Show show4 = new Show(movie2, theaterB, LocalDateTime.parse("2023-08-21T14:00:00"), new BigDecimal(110));
        Show show5 = new Show(movie3, theaterB, LocalDateTime.parse("2023-08-21T16:00:00"), new BigDecimal(110));
        Show show6 = new Show(movie4, theaterB, LocalDateTime.parse("2023-08-21T18:00:00"), new BigDecimal(120));
        showRepository.saveAll(Arrays.asList(show1, show2, show3, show4));
    }
}


