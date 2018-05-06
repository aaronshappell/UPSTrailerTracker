package com.ups.UPSTrailerTracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrailerController {
    @Autowired
    private TrailerService trailerService;

    @GetMapping("/trailers")
    public List<Trailer> trailers() {
        return trailerService.getTrailers();
    }
}
