package com.cvp.oscarperez;

import com.cvp.oscarperez.entities.City;
import com.cvp.oscarperez.repository.CityRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OscarperezApplication {

	@Autowired
	CityRepository cityRepository;
	public static void main(String[] args) {
		SpringApplication.run(OscarperezApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			cityRepository.save(City.builder().name("Bogota").build());
			cityRepository.save(City.builder().name("Cali").build());
			cityRepository.save(City.builder().name("Medellin").build());
			cityRepository.save(City.builder().name("Bucaramanga").build());
			cityRepository.save(City.builder().name("Pasto").build());
		};
	}
}
