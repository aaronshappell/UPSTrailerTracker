package com.ups.UPSTrailerTracker

import com.ups.UPSTrailerTracker.storage.StorageProperties
import com.ups.UPSTrailerTracker.storage.StorageService
import com.ups.UPSTrailerTracker.trailer.Trailer
import com.ups.UPSTrailerTracker.trailer.TrailerService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties::class)
class UpsTrailerTrackerApplication {
	@Bean
	fun init(storageService: StorageService, trailerService: TrailerService): CommandLineRunner {
		return CommandLineRunner {
			storageService.deleteAll()
			storageService.init()

			trailerService.addTrailer(Trailer(123, 43653, 12000, 500, 35, 4, 11.0))
			trailerService.addTrailer(Trailer(456, 32156, 13200, 230, 31, 2, 12.0))
		}
	}
}

fun main(args: Array<String>) {
	runApplication<UpsTrailerTrackerApplication>(*args)
}
