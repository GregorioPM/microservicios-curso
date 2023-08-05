package com.curso.servicio.productos.services;

import com.curso.commons.models.entity.Producto;

import java.util.List;

public interface IProductoService {

    List<Producto> findAll();
    Producto findById(Long id);
    Producto save(Producto producto);
    void deleteById(Long id);

}
