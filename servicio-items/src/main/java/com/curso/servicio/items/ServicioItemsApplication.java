package com.curso.servicio.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.curso.commons.models.entity"})//que busque las entitys en commons
public class ServicioItemsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServicioItemsApplication.class, args);
	}

}
