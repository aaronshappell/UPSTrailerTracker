package com.ups.UPSTrailerTracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrailerService {
    @Autowired
    private TrailerRepository trailerRepository;

    public void addTrailer(Trailer trailer) {
        trailerRepository.save(trailer);
    }

    public List<Trailer> getTrailers(){
        return (List) trailerRepository.findAll();
    }
}
