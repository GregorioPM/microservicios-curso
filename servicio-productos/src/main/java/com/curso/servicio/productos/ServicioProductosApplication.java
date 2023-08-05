package com.curso.servicio.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.curso.commons.models.entity"})//que busque las entitys en commons
public class ServicioProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioProductosApplication.class, args);
	}

}
