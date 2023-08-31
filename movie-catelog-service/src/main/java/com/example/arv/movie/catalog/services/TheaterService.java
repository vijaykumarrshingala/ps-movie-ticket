package com.example.arv.movie.catalog.services;

import com.example.arv.movie.catalog.entities.Show;
import com.example.arv.movie.catalog.entities.Theater;
import com.example.arv.movie.catalog.repositories.ShowRepository;
import com.example.arv.movie.catalog.repositories.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterService {


    private final TheaterRepository theaterRepository;
    private final ShowRepository showRepository;

    public Theater getTheaterById(Long theaterId) {
        return theaterRepository.findById(theaterId).orElse(null);
    }

    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    public List<Show> getShowsByTheaterId(Long theaterId) {
        Theater theater = theaterRepository.findById(theaterId).orElse(null);
        return theater != null ? showRepository.findByTheater(theater) : Collections.singletonList(new Show());

    }

    public Theater createTheater(Theater theater) {
        return theaterRepository.save(theater);
    }

    public Theater updateTheater(Long theaterId, Theater theater) {
        // Implement logic to update the theater with the given ID and save the changes
        return theaterRepository.save(theater);
    }

    public boolean deleteTheater(Long theaterId) {
        // Implement logic to delete the theater with the given ID
        // Return true if deletion was successful, otherwise return false
        return false;
    }
}

