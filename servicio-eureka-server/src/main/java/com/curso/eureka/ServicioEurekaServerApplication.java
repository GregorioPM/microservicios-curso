package com.curso.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServicioEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioEurekaServerApplication.class, args);
	}

}
