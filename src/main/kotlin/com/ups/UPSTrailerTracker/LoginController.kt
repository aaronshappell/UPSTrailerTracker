package com.ups.UPSTrailerTracker

import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
    @GetMapping("/login")
    fun login(model: Model): String {
        val auth: Authentication? = SecurityContextHolder.getContext().authentication
        if(auth != null && auth !is AnonymousAuthenticationToken){
            return "redirect:/trailers"
        }
        model.addAttribute("view", "login")
        return "layout"
    }
}