package dev.connor.Carsharingplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CarSharingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarSharingPlatformApplication.class, args);
	}

}
