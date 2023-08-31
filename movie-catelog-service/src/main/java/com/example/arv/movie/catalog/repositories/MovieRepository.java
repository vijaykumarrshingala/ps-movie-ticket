package com.example.arv.movie.catalog.repositories;

import com.example.arv.movie.catalog.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}

