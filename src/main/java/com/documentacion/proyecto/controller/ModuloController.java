package com.documentacion.proyecto.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.documentacion.proyecto.dto.ModuloDTO;

@Controller
public class ModuloController {
    @GetMapping("/formulario-modulo")
    public String mostrarFormulario(Model model){
        model.addAttribute("moduloForm",new ModuloDTO());
        return "formulario-modulo";
    }

    @PostMapping("/procesar-modulo")
    public String procesarFormulario(
        @ModelAttribute("moduloForm") ModuloDTO moduloDTO, 
        @RequestParam("apis") List<String> apis, // Recibe los APIs como lista
        Model model){
        moduloDTO.setApis(apis); // Asigna las APIs al DTO

        //Aqui procesamos los datos (guardar en BD, etc)...

        System.out.println("Modulo recibido:" + moduloDTO.toString());
        System.out.println("Apis recibidas"+ moduloDTO.getApis());

        model.addAttribute("modulo",moduloDTO);
        return "resultado";
    } 
}
