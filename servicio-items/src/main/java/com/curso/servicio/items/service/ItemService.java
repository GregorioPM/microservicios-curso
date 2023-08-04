package com.curso.servicio.items.service;

import com.curso.servicio.items.models.Item;
import com.curso.servicio.items.models.Producto;

import java.util.List;

public interface ItemService {

    List<Item> findAll();
    Item findById(Long id, Integer cantidad);
    Producto save(Producto producto);
    Producto update(Producto producto, Long id);

    void delete(Long id);

}
