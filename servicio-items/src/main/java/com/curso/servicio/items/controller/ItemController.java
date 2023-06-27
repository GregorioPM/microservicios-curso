package com.curso.servicio.items.controller;

import com.curso.servicio.items.models.Item;
import com.curso.servicio.items.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ItemController {

    @Qualifier("serviceRestTemplate")
    private final ItemService service;

    @GetMapping("/listar")
    public List<Item> listar() {
        return service.findAll();
    }

    @GetMapping("/ver/{id}/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad){
        return service.findById(id, cantidad);
    }

}
