package com.example.arv.booking.httpclient;


import com.example.arv.booking.model.Show;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "movie-catalog-service", fallback = ShowFeignFallback.class)
public interface ShowFeignClient {

    @GetMapping("/api/v1/shows/{id}")
    Show getShowById(@PathVariable("id") Long id);
}
