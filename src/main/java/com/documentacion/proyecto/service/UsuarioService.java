package com.documentacion.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.documentacion.proyecto.model.Rol;
import com.documentacion.proyecto.model.Usuario;
import com.documentacion.proyecto.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registrarUsuario(Usuario usuario){
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRol(Rol.USER); //Rol por defecto
        usuario.setNombre(usuario.getNombre());
        usuario.setUsername(usuario.getUsername());
        usuarioRepo.save(usuario);
    }
}
