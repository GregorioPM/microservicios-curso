package com.curso.servicio.usuarios.repository;

import com.curso.usuarios.commons.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "usuarios")
public interface UsuarioRepository extends CrudRepository<Usuario, Long> , PagingAndSortingRepository<Usuario, Long>{

    @RestResource(path = "buscar-username")
    Usuario findByUsername(@Param("username") String username);

    @Query("select u from Usuario u where u.username = ?1")
    Usuario obtenerPorUsername(String username);

}
