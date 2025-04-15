package com.documentacion.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.documentacion.proyecto.model.Usuario;
import com.documentacion.proyecto.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UsuarioRepository usuarioRepo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario usuario = usuarioRepo.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new org.springframework.security.core.userdetails.User(
            usuario.getUsername(),
            usuario.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_"+usuario.getRol().name()))
        );
    }
}
