package com.curso.servicio.items.controller;

import com.curso.servicio.items.models.Item;
import com.curso.servicio.items.models.Producto;
import com.curso.servicio.items.service.ItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@Log4j2
public class ItemController {

    @Qualifier("serviceFeign")
    private final ItemService service;

    private final CircuitBreakerFactory cbFactory;

    @GetMapping("/listar")
    public List<Item> listar(@RequestParam(name = "nombre", required = false) String nombre,
                             @RequestHeader(name = "token-request", required = false) String token) {
        log.info("nombre: " + nombre);
        log.info("token: " + token);
        return service.findAll();
    }

    //@HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/ver/{id}/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad){
        //return service.findById(id, cantidad);
        return cbFactory.create("items")
                .run(()-> service.findById(id, cantidad),
                        e -> metodoAlternativo(id, cantidad, e));
    }

    @CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo")
    @GetMapping("/ver2/{id}/{cantidad}")
    public Item detalle2(@PathVariable Long id, @PathVariable Integer cantidad){
        return service.findById(id, cantidad);
    }

    //Solo timelimiter No aplica el estado open en circuit breaker
    @CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo2")
    @TimeLimiter(name = "items")
    @GetMapping("/ver3/{id}/{cantidad}")
    public CompletableFuture<Item> detalle3(@PathVariable Long id, @PathVariable Integer cantidad){
        return CompletableFuture.supplyAsync(() -> service.findById(id, cantidad));
    }

    public Item metodoAlternativo(Long id, Integer cantidad, Throwable e){
        log.info("Error: " + e.getMessage());
        Item item = new Item();
        Producto producto = new Producto();
        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Camara");
        producto.setPrecio(500.00);
        item.setProducto(producto);
        return item;
    }

    public CompletableFuture<Item> metodoAlternativo2(Long id, Integer cantidad, Throwable e){
        log.info("Error: " + e.getMessage());
        Item item = new Item();
        Producto producto = new Producto();
        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Camara");
        producto.setPrecio(500.00);
        item.setProducto(producto);
        return CompletableFuture.supplyAsync(() -> item);
    }

}
