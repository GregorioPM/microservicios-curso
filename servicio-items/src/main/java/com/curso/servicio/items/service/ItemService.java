package com.curso.servicio.items.service;

import com.curso.commons.models.entity.Producto;
import com.curso.servicio.items.models.Item;

import java.util.List;

public interface ItemService {

    List<Item> findAll();
    Item findById(Long id, Integer cantidad);
    Producto save(Producto producto);
    Producto update( Long id, Producto producto);

    void delete(Long id);

}
