package com.curso.servicio.productos.repository;

import com.curso.servicio.productos.models.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Long> {



}
