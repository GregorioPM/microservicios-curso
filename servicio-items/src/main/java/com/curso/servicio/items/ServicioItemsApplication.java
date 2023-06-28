package com.curso.servicio.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
public class ServicioItemsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServicioItemsApplication.class, args);
	}

}
