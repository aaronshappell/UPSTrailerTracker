package com.ups.UPSTrailerTracker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrailerRepository extends CrudRepository<Trailer, Integer> {
	List<Trailer> findAll();
}
