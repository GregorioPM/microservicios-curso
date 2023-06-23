package com.curso.servicio.items;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean("productoRest")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
