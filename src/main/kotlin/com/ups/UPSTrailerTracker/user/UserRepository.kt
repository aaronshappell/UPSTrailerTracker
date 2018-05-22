package com.ups.UPSTrailerTracker.user

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
}