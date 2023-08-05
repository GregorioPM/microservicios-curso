package com.curso.servicio.items.models;

import com.curso.commons.models.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Producto producto;

    private Integer cantidad;

    public Double getTotal(){
        return producto.getPrecio() * cantidad.doubleValue();
    }

}
