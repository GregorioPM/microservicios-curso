package com.curso.gateway.filters.factory;

import lombok.Data;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Log4j2
@Component
public class EjemploGatewayFilterFactory extends AbstractGatewayFilterFactory<EjemploGatewayFilterFactory.Configuracion> {

    public EjemploGatewayFilterFactory() {
        super(Configuracion.class);
    }

    @Override
    public GatewayFilter apply(Configuracion config) {
        //Se retorna un OrderedGatewayFilter para saber que orden tiene el filtro si no es necesario se quita
        return new OrderedGatewayFilter((exchange, chain) -> {
            log.info("Ejecutando Filtro Pre gateway filter factory: " + config.mensaje);
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {

                Optional.ofNullable(config.cookieValor).ifPresent(cookie -> {
                    exchange.getResponse().addCookie(ResponseCookie.from(config.cookieNombre, cookie).build());
                });

                log.info("Ejecutando Filtro Post gateway filter factory: " + config.mensaje);

            }));
        },2);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("Mensaje","cookieNombre","cookieValor");
    }

    @Override
    public String name() {
        return "EjemploCookie";
    }

    @Data
    public static class Configuracion {
        private String mensaje;
        private String cookieValor;
        private String cookieNombre;
    }
}
