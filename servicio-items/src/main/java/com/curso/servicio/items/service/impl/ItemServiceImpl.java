package com.curso.servicio.items.service.impl;

import com.curso.servicio.items.models.Item;
import com.curso.servicio.items.models.Producto;
import com.curso.servicio.items.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final String RUTA_PRODUCTO = "http://servicio-productos";

    private final RestTemplate restTemplate;
    @Override
    public List<Item> findAll() {
        //ResponseEntity<Producto> productoResponseEntity = restTemplate.getForEntity(rutaProducto + "/listar", Producto.class);
        List<Producto> productos = Arrays.asList(restTemplate.getForObject(RUTA_PRODUCTO + "/listar", Producto[].class));
        return productos.stream()
                .map(p -> new Item(p,1))
                .collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        Producto producto = restTemplate.getForObject(RUTA_PRODUCTO + "/ver/{id}", Producto.class, pathVariables);
        return new Item(producto, cantidad);
    }

    @Override
    public Producto save(Producto producto) {
        HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
        ResponseEntity<Producto> response = restTemplate.exchange(RUTA_PRODUCTO + "/crear", HttpMethod.POST, body, Producto.class);
        return response.getBody();
    }

    @Override
    public Producto update(Producto producto, Long id) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
        ResponseEntity<Producto> response = restTemplate.exchange(RUTA_PRODUCTO + "/editar/{id}",
                HttpMethod.PUT, body, Producto.class, pathVariables);
        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        restTemplate.delete(RUTA_PRODUCTO + "/eliminar/{id}", pathVariables);
    }
}
