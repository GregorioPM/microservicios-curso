package com.curso.servicio.items.service.impl;

import com.curso.servicio.items.clientes.ProductoClienteRest;
import com.curso.servicio.items.models.Item;
import com.curso.servicio.items.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
@AllArgsConstructor
public class ItemServiceFeign implements ItemService {

    private ProductoClienteRest clienteFeign;


    @Override
    public List<Item> findAll() {
        return clienteFeign.listar().stream()
                .map(p -> new Item(p, 1))
                .collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(clienteFeign.detalle(id), cantidad);
    }
}
