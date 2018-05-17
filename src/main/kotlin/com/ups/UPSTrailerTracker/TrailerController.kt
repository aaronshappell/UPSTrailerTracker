package com.ups.UPSTrailerTracker

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TrailerController {
    @Autowired
    lateinit var trailerService: TrailerService

    @GetMapping("/trailers")
    fun trailers(): ArrayList<Trailer> = trailerService.getTrailers()
}