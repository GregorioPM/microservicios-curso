package com.curso.oauth.services;

import com.curso.oauth.clients.UsuarioFeignClient;
import com.curso.usuarios.commons.models.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class UsuarioService implements IUsuarioService, UserDetailsService {
    private final UsuarioFeignClient client;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = client.findByUsername(username);
        if(usuario == null){
            log.error("Error en el login, no existe el usuario en el sistema " + username);
            throw new UsernameNotFoundException("Error en el login, no existe el usuario en el sistema " + username);
        }
        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> log.info("Role : " + authority.getAuthority()))
                .collect(Collectors.toList());
        log.info("Usuario autenticado: " + username);

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(),
                true, true, true, authorities);
    }

    @Override
    public Usuario findByUsername(String username) {
        return client.findByUsername(username);
    }
}
