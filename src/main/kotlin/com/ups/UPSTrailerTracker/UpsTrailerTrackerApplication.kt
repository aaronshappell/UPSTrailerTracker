package com.ups.UPSTrailerTracker

import com.ups.UPSTrailerTracker.storage.StorageProperties
import com.ups.UPSTrailerTracker.storage.StorageService
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

			//identifactionNumber, originNumber, volume, smalls, bags, handles, planHours
			Array<Double> trailerInfo1 = arrayOf(2, 43653, 12000, 500, 35, 4, 11)
			Array<Double> trailerInfo1 = arrayOf(1, 32156, 13200, 230, 31, 2, 12)

			trailerService.addTrailer(Trailer(trailerInfo1));
			trailerService.addTrailer(Trailer(trailerInfo2));
		}
	}
}

fun main(args: Array<String>) {
	runApplication<UpsTrailerTrackerApplication>(*args)
}
