package com.documentacion.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormularioDTO {
    @NotBlank(message = "El mensaje no puede estar vacio")
    private String mensaje;

    @NotBlank
    private String prioridad;
}
