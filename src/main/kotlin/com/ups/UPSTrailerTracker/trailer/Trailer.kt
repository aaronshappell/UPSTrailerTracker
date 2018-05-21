package com.ups.UPSTrailerTracker.trailer

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Trailer(
    val trailerNumber: Int,
    val originNumber: Int,
    val volume: Int,
    val smalls: Int,
    val bags: Int,
    val handles: Int,
    val planHours: Double,

    var planStart: Double = 0.0,
    var planFinish: Double = 0.0,
    var bayDoorNumber: Int = 0,
    var currentPercentage: Double = 0.0,
    var actualStart: Double = 0.0,
    var warningReady: Boolean = false,
    var actualFinish: Double = 0.0,
    var minute30: Double = 0.0,
    var minute60: Double = 0.0,
    var minute90: Double = 0.0,
    var timeCalledClear: Double = 0.0,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
)

