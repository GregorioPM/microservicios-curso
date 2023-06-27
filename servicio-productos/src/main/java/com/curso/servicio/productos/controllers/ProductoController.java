package com.curso.servicio.productos.controllers;

import com.curso.servicio.productos.models.entity.Producto;
import com.curso.servicio.productos.services.IProductoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductoController {

    private final IProductoService productoService;

    private final Environment env;

    @Value("${server.port}")
    private Integer port;
    //-Dserver.port=9001
    @GetMapping("/listar")
    public List<Producto> getAll() {
        return productoService.findAll().stream()
                .map(p -> {
                    //p.setPort(Integer.parseInt(env.getProperty("local.server.port")));
                    p.setPort(port);
                    return p;
                }).collect(Collectors.toList());
    }

    @GetMapping("/ver/{id}")
    public Producto getById(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        //producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        producto.setPort(port);
        return producto;
    }

}
