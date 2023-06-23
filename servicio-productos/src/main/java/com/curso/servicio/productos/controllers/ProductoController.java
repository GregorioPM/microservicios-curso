package com.curso.servicio.productos.controllers;

import com.curso.servicio.productos.models.entity.Producto;
import com.curso.servicio.productos.services.IProductoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductoController {

    private final IProductoService productoService;

    @GetMapping("/listar")
    public List<Producto> getAll(){
        return productoService.findAll();
    }

    @GetMapping("/ver/{id}")
    public Producto getById(@PathVariable Long id){
        return productoService.findById(id);
    }

}
