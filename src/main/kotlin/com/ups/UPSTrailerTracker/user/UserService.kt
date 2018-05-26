package com.ups.UPSTrailerTracker.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService : UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String): UserDetails? {
        val user: User = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("No user found with username: $username")
        return org.springframework.security.core.userdetails.User(user.username, user.password, getAuthorities(user.roles))
    }

    fun addUser(user: User){
        userRepository.save(user)
    }

    private fun getAuthorities(roles: ArrayList<String>): ArrayList<GrantedAuthority> {
        val authorities: ArrayList<GrantedAuthority> = ArrayList<GrantedAuthority>()
        for(role in roles){
            authorities.add(SimpleGrantedAuthority("ROLE_$role"))
        }
        return authorities
    }
}