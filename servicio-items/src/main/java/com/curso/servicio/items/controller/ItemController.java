package com.curso.servicio.items.controller;

import com.curso.servicio.items.models.Item;
import com.curso.servicio.items.models.Producto;
import com.curso.servicio.items.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ItemController {

    @Qualifier("serviceFeign")
    private final ItemService service;

    @GetMapping("/listar")
    public List<Item> listar() {
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
