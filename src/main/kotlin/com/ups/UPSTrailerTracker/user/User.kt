package com.ups.UPSTrailerTracker.user

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
    var username: String,
    var password: String,
    var roles: ArrayList<String>,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
)