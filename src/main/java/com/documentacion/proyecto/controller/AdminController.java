package com.documentacion.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.documentacion.proyecto.model.Rol;
import com.documentacion.proyecto.model.Usuario;
import com.documentacion.proyecto.service.UsuarioService;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')") // solo admins
public class AdminController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crear-admin")
    public String crearAdmin(@ModelAttribute Usuario usuario){
        usuario.setRol(Rol.ADMIN);
        usuarioService.registrarUsuario(usuario);
        return "redirect:/admin/dashboard";
    }

}
