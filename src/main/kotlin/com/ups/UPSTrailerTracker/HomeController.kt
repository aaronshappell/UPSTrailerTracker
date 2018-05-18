package com.ups.UPSTrailerTracker

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    @GetMapping("/")
    fun home(model: Model): String {
        model.addAttribute("view", "home")
        return "layout"
    }
}