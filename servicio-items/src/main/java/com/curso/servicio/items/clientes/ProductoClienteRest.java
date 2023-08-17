package com.curso.servicio.items.clientes;

import com.curso.commons.models.entity.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {

    @GetMapping("/listar")
    public List<Producto> listar();

    @GetMapping("/ver/{id}")
    public Producto detalle(@PathVariable Long id);

    @PostMapping("/crear")
    public Producto crear(@RequestBody Producto producto);

    @PutMapping("/editar/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto producto);

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id);
}
