package com.curso.servicio.items.service;

import com.curso.servicio.items.models.Item;

import java.util.List;

public interface ItemService {

    List<Item> findAll();
    Item findById(Long id, Integer cantidad);

}
