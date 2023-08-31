package com.example.arv.movie.catalog.controllers;

import com.example.arv.movie.catalog.entities.Show;
import com.example.arv.movie.catalog.entities.Theater;
import com.example.arv.movie.catalog.services.TheaterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/theaters")
public class TheaterController {

    private TheaterService theaterService; // Inject your service here

    @GetMapping("/{theaterId}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Long theaterId) {
        Theater theater = theaterService.getTheaterById(theaterId);

        if (theater == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(theater);
    }

    @GetMapping
    public ResponseEntity<List<Theater>> getAllTheaters() {
        List<Theater> theaters = theaterService.getAllTheaters();
        return ResponseEntity.ok(theaters);
    }

    @GetMapping("/{theaterId}/shows")
    public ResponseEntity<List<Show>> getShowsByTheaterId(@PathVariable Long theaterId) {
        List<Show> shows = theaterService.getShowsByTheaterId(theaterId);
        return ResponseEntity.ok(shows);
    }

    @PostMapping
    public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) {
        Theater createdTheater = theaterService.createTheater(theater);
        return ResponseEntity.created(URI.create("/api/v1/theaters/" + createdTheater.getId())).body(createdTheater);
    }

    @PutMapping("/{theaterId}")
    public ResponseEntity<Theater> updateTheater(@PathVariable Long theaterId, @RequestBody Theater theater) {
        Theater updatedTheater = theaterService.updateTheater(theaterId, theater);

        if (updatedTheater == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedTheater);
    }

    @DeleteMapping("/{theaterId}")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long theaterId) {
        boolean deleted = theaterService.deleteTheater(theaterId);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}

