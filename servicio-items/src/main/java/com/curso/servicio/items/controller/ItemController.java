package com.curso.servicio.items.controller;

import com.curso.servicio.items.models.Item;
import com.curso.servicio.items.models.Producto;
import com.curso.servicio.items.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Log4j2
public class ItemController {

    @Qualifier("serviceFeign")
    private final ItemService service;

    @GetMapping("/listar")
    public List<Item> listar(@RequestParam(name = "nombre", required = false) String nombre,
                             @RequestHeader(name = "token-request", required = false) String token) {
        log.info("nombre: " + nombre);
        log.info("token: " + token);
        return service.findAll();
    }

    @HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/ver/{id}/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad){
        return service.findById(id, cantidad);
    }

    public Item metodoAlternativo(Long id, Integer cantidad){
        Item item = new Item();
        Producto producto = new Producto();
        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Camara");
        producto.setPrecio(500.00);
        item.setProducto(producto);
        return item;
    }

}
