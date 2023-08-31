package com.example.arv.movie.catalog.repositories;

import com.example.arv.movie.catalog.entities.Show;
import com.example.arv.movie.catalog.entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {

    List<Show> findByTheater(Theater theater);
}

