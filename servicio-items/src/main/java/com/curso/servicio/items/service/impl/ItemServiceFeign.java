package com.curso.servicio.items.service.impl;

import com.curso.servicio.items.clientes.ProductoClienteRest;
import com.curso.servicio.items.models.Item;
import com.curso.servicio.items.models.Producto;
import com.curso.servicio.items.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
@AllArgsConstructor
@Primary
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

    @Override
    public Producto save(Producto producto) {

        return clienteFeign.crear(producto);
    }

    @Override
    public Producto update(Producto producto, Long id) {
        return clienteFeign.update(producto, id);
    }

    @Override
    public void delete(Long id) {
        clienteFeign.eliminar(id);
    }
}
