package com.ups.UPSTrailerTracker;

import com.ups.UPSTrailerTracker.storage.StorageProperties;
import com.ups.UPSTrailerTracker.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class UpsTrailerTrackerApplication {
	public static void main(String[] args) {
		SpringApplication.run(UpsTrailerTrackerApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService, TrailerService trailerService){
		return (args) -> {
			storageService.deleteAll();
			storageService.init();

			//identifactionNumber, originNumber, volume, smalls, bags, handles, planHours
			double[] trailerInfo = {
				2, 43653, 12000, 500, 35, 4, 11
			};

			trailerService.addTrailer(new Trailer(trailerInfo));
		};
	}
}
