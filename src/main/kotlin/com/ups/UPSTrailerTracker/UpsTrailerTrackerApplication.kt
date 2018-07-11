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
		}
	}
}

fun main(args: Array<String>) {
	runApplication<UpsTrailerTrackerApplication>(*args)
}
