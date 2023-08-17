package com.curso.oauth.services;

import com.curso.usuarios.commons.models.entity.Usuario;

public interface IUsuarioService {

    Usuario findByUsername(String username);
}
