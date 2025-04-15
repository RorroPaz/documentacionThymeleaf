package com.documentacion.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.documentacion.proyecto.model.Usuario;
import com.documentacion.proyecto.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String mostrarLogin(){
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarLogin(Model model){
        model.addAttribute("usuario",new Usuario()); //Le pasamos un objeto par aque lo llene
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(@Valid @ModelAttribute Usuario usuario, BindingResult result){ //Valida el modelo (objeto usuario), Binding cacha el error o resultado
        if(result.hasErrors()){
            return "registro";
        }
        usuarioService.registrarUsuario(usuario);
        return "redirect:/login?registroExitoso";
    }
}
