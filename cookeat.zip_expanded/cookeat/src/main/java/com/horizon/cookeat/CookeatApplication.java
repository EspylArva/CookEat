package com.horizon.cookeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EntityScan( basePackages = {"com.horizon.cookeat.entities"} )
public class CookeatApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookeatApplication.class, args);
	}

}
