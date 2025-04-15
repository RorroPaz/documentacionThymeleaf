package com.documentacion.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.documentacion.proyecto.dto.FormularioDTO;


@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String mostrarDashboard(Model model){
        //Agrega un objeto DTO vacio al modelo para el formulario
        model.addAttribute("datoForm", new FormularioDTO());
        return "dashboard";
    }

    @PostMapping("/procesar-formulario")
    public String procesarFormulario(@Validated @ModelAttribute("datoForm") FormularioDTO formulario, BindingResult result ,Model model){
        // BindingResult debe estar justo despues del @Valid
        if(result.hasErrors()){
            return "dashboard"; // Muestra los erroes en el template
        }
        // Logica de procesamiento (ej: guardar en BD, validar, etc. )
        String resultado = "Mensaje recibido: '" + formulario.getMensaje()
        + "' con prioridad: " + formulario.getPrioridad();

        //Agregar el resultado al modelo para mostrarlo en la vista 
        model.addAttribute(("resultado"), resultado);
        System.out.println(resultado);

        //Redirige a la misma pagina (dashboard) para  mostrar el resultado
        return "dashboard";
    }
}
