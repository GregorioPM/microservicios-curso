package com.curso.gateway.filters;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Log4j2
@Component
public class GlobalFilters implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Ejecutando Filtro Pre");
        exchange.getRequest().mutate().headers(h -> h.add("token", "123456"));
        return chain.filter(exchange).then(Mono.fromRunnable( ()-> {
            log.info("Ejecutando Filtro Post");
            Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token"))
                    .ifPresent(valor -> exchange.getResponse().getHeaders().add("token", valor));
            exchange.getResponse().getCookies().add("color", ResponseCookie.from("Color", "rojo").build());
            //exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        }));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
