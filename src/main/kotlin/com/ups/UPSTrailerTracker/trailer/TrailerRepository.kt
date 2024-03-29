package com.ups.UPSTrailerTracker.trailer

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TrailerRepository : CrudRepository<Trailer, Long> {
    override fun findAll(): ArrayList<Trailer>
}