package com.example.arv.movie.catalog.controllers;


import com.example.arv.movie.catalog.constants.ShowStatus;
import com.example.arv.movie.catalog.entities.Show;
import com.example.arv.movie.catalog.services.ShowService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/shows")
@RequiredArgsConstructor
public class ShowController {



    private final ShowService showService;

    private final static Logger LOG = LoggerFactory.getLogger(ShowController.class);


    //Can be added based on role check @PreAuthorize("hasRole('ROLE_THEATER_OWNER')")
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Show> cancelShow(@PathVariable Long id) {

        LOG.info("Start cancelShow for showId : {}", id);

        Optional<Show> showOptional = showService.findShowById(id);

        if (showOptional.isPresent()) {
            Show show = showOptional.get();
            if (show.getStatus() != ShowStatus.CANCELED) { // Check if the show is not already canceled
                Optional<Show> optionalShow = showService.cancelShow(show);
                LOG.info("Completed cancelShow for showId : {}", id);
                return ResponseEntity.ok(optionalShow.get());
            } else {
                return ResponseEntity.badRequest().body(new Show());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Show> getAllShows() {
        return showService.getAllShows();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Show> findShowById(@PathVariable Long id) {
        Optional<Show> showOptional = showService.findShowById(id);
        if (showOptional.isPresent()) {
            return ResponseEntity.ok(showOptional.get());
        } else {
            return ResponseEntity.badRequest().body(new Show());
        }

    }

}
