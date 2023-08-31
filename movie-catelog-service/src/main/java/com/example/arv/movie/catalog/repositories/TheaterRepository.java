package com.example.arv.movie.catalog.repositories;

import com.example.arv.movie.catalog.entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
}

