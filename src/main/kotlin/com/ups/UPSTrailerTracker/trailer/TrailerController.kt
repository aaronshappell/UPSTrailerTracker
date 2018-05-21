package com.ups.UPSTrailerTracker.trailer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class TrailerController {
    @Autowired
    lateinit var trailerService: TrailerService

    @GetMapping("/")
    fun home(): String = "redirect:/trailers"

    @GetMapping("/trailers")
    fun getTrailersView(model: Model): String {
        model.addAttribute("trailers", trailerService.getTrailers())
        model.addAttribute("view", "trailers")
        return "layout"
    }

    // Requires header "Accept:application/json"
    @GetMapping("/trailers", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    @ResponseBody
    fun getTrailerJSON(): ArrayList<Trailer> = trailerService.getTrailers()

    @GetMapping("/trailer/{id}")
    fun getTrailerView(@PathVariable("id") id: Long, model: Model): String {
        val trailer: Trailer? = trailerService.getTrailer(id)
        if(trailer != null) {
            model.addAttribute("trailer", trailer)
            model.addAttribute("view", "trailer")
        } else {
            model.addAttribute("view", "noTrailer")
        }
        return "layout"
    }
}