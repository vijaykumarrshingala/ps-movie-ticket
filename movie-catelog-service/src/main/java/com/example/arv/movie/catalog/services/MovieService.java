package com.example.arv.movie.catalog.services;

import com.example.arv.movie.catalog.entities.Movie;
import com.example.arv.movie.catalog.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final Logger logger = LoggerFactory.getLogger(MovieService.class);

    public List<Movie> getAllMovies() {
        logger.info("getAllMovies.....");
        return movieRepository.findAll();
    }
}

