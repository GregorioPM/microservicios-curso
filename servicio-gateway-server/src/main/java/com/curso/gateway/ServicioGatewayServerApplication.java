package com.curso.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServicioGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioGatewayServerApplication.class, args);
	}

}
