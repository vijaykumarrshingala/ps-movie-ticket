package com.example.arv.booking.httpclient;

import com.example.arv.booking.model.Show;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ShowFeignFallback implements ShowFeignClient {

    private final static Logger LOG = LoggerFactory.getLogger(ShowFeignFallback.class);
    @Override
    public Show getShowById(Long id) {
        LOG.error("ShowFeignFallback's getShowById called for showId {}", id);
        return new Show();
    }
}
