package com.curso.servicio.productos.models.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name = "Productos")
@Data
public class Producto implements Serializable {

    private static final long serialVersionUID = 1285454306356845809L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private  Double precio;
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

}
