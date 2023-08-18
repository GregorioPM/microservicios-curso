package com.curso.oauth.event;

import com.curso.oauth.services.IUsuarioService;
import com.curso.usuarios.commons.models.entity.Usuario;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@AllArgsConstructor
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    private final IUsuarioService usuarioService;

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        if(authentication.getDetails() instanceof WebAuthenticationDetails){
        //if(authentication.getName().equalsIgnoreCase("frontendapp")){
            return;
        }
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String mensaje = "Success Login " + user.getUsername();
        System.out.println(mensaje);
        log.info(mensaje);
        Usuario usuario = usuarioService.findByUsername(authentication.getName());
        if(usuario.getIntentos() != null && usuario.getIntentos() > 0){
            usuario.setIntentos(0);
            usuarioService.update(usuario.getId(), usuario);
        }
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException e, Authentication authentication) {
        String mensaje = "Error en el login " + e.getMessage();
        System.out.println(mensaje);
        log.info(mensaje);
        try {
            Usuario usuario = usuarioService.findByUsername(authentication.getName());
            if(usuario.getIntentos() == null){
                usuario.setIntentos(0);
            }
            log.info("Intentos actual es de: " + usuario.getIntentos());
            usuario.setIntentos(usuario.getIntentos() + 1);
            log.info("Intentos despues es de: " + usuario.getIntentos());
            if(usuario.getIntentos() >= 3){
                log.error(String.format("El usuario %s deshabilitado por maximo intentos.", authentication.getName()));
                usuario.setEnabled(false);
            }
            usuarioService.update(usuario.getId(), usuario);
        } catch (FeignException exception) {
            log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
        }

    }
}
