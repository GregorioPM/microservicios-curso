package com.curso.servicio.productos.controllers;

import com.curso.commons.models.entity.Producto;
import com.curso.servicio.productos.services.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
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
                    p.setPort(Integer.parseInt(env.getProperty("local.server.port")));
                    //p.setPort(port);
                    return p;
                }).collect(Collectors.toList());
    }

    @GetMapping("/ver/{id}")
    public Producto getById(@PathVariable Long id) throws InterruptedException {
        if(id.equals(10L)){
            throw new IllegalStateException("Producto no Encontrado");
        }
        if(id.equals(7L)){
            TimeUnit.SECONDS.sleep(5L);
        }
        Producto producto = productoService.findById(id);
        //producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        //producto.setPort(port);
        return producto;
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@RequestBody Producto producto) {
        return productoService.save(producto);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@RequestBody Producto producto, @PathVariable Long id) {
       Producto productoBD = productoService.findById(id);
       productoBD.setNombre(producto.getNombre());
       productoBD.setPrecio(producto.getPrecio());
       return productoService.save(productoBD);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        productoService.deleteById(id);
    }
}
