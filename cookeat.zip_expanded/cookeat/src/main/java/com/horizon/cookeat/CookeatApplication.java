package com.horizon.cookeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan( basePackages = {"com.horizon.cookeat.model"} )
public class CookeatApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookeatApplication.class, args);
	}

}
