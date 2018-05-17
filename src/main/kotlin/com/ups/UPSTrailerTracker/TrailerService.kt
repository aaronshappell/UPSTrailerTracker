package com.ups.UPSTrailerTracker

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TrailerService {
    @Autowired
    lateinit var trailerRepository: TrailerRepository

    fun addTrailer(trailer: Trailer) {
        trailerRepository.save(trailer)
    }

    fun getTrailers(): List<Trailer> = trailerRepository.findAll()
}