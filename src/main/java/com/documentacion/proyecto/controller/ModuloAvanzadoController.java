package com.documentacion.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.documentacion.proyecto.dto.ApiDataDTO;
import com.documentacion.proyecto.dto.ModuloAvanzadoDTO;

@Controller
public class ModuloAvanzadoController {
    @GetMapping("/formulario-modulo-avanzado")
    public String mostrarFormulario(Model model) {
        // Enviaremos una primera API vacia
        ModuloAvanzadoDTO moduloADTO = new ModuloAvanzadoDTO();
        moduloADTO.getApis().add(new ApiDataDTO());

        model.addAttribute("moduloForm", moduloADTO);
        return "formulario-modulo-avanzado";
    }

    @PostMapping("/procesar-modulo-avanzado")
    public String procesarFormulario(
            @ModelAttribute ModuloAvanzadoDTO moduloADTO,
            Model model) {

        System.out.println(moduloADTO.toString());
        System.out.println(moduloADTO.getApis());
        model.addAttribute("modulo", moduloADTO);
        return "resultadoAvanzado";
    }

    @PostMapping("/generar-pdf")
    public String generaPdf(@ModelAttribute ModuloAvanzadoDTO moduloADTO) {
        System.out.println("Datos: " + moduloADTO.toString());
        System.out.println(moduloADTO.getApis());
        return "redirect:/formulario-modulo-avanzado";
    }
}
