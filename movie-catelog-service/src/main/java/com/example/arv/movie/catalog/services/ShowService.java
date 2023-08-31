package com.example.arv.movie.catalog.services;

import com.example.arv.movie.catalog.constants.ShowStatus;
import com.example.arv.movie.catalog.entities.Show;
import com.example.arv.movie.catalog.repositories.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ShowService {

    private final static Logger LOG = LoggerFactory.getLogger(ShowService.class);

    private final ShowRepository showRepository;

    /**
     * Cancel the show based on Show id.
     * @param id
     * @return Optional<Show>
     */
    public Optional<Show> cancelShow(Show show) {

        LOG.info("Started ShowService's cancelShow for showId {}", show.getId());
        show.setStatus(ShowStatus.CANCELED);
        show = showRepository.save(show);
        LOG.info("theater name {}, show time {}, movie name {}, show status {} ",
                show.getTheater().getName(), show.getShowTime(), show.getMovie().getTitle(), show.getStatus().name());
        LOG.info("Completed ShowService's cancelShow for showId {}", show.getId());
        return Optional.of(show);
    }

    public Optional<Show> findShowById(Long id){
        return showRepository.findById(id);
    }

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    //We can add more endpoint to schedule show, update it etc. based on business needs
}
