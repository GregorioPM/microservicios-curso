package com.curso.servicio.productos.services.impl;

import com.curso.servicio.productos.models.entity.Producto;
import com.curso.servicio.productos.repository.ProductoRepository;
import com.curso.servicio.productos.services.IProductoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductoServiceImpl implements IProductoService {

    private final ProductoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return (List<Producto>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
