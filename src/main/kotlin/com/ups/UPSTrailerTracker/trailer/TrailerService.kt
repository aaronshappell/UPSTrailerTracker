package com.ups.UPSTrailerTracker.trailer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TrailerService {
    @Autowired
    lateinit var trailerRepository: TrailerRepository

    fun addTrailer(trailer: Trailer) {
        trailerRepository.save(trailer)
    }

    fun getTrailers(): ArrayList<Trailer> = trailerRepository.findAll()

    fun getTrailer(id: Long): Trailer? {
        return trailerRepository.findById(id).orElse(null)
    }
}