package com.ferchau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan ({"com.ferchau", "com.ferchau.config", "com.ferchau.dao", "com.ferchau.service", "com.ferchau.service.implXJ"})
@EnableAutoConfiguration
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
