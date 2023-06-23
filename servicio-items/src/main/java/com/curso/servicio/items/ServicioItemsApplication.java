package com.curso.servicio.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@RibbonClient(name = "servicio-productos")
@SpringBootApplication
public class ServicioItemsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServicioItemsApplication.class, args);
	}

}
