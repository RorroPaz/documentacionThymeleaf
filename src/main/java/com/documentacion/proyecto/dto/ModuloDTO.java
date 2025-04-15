package com.documentacion.proyecto.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ModuloDTO {
    @NotBlank
    private String desarrollador;

    @NotBlank
    private String nombreModulo;

    @NotBlank
    private String rutaModulo;

    @NotBlank
    private String javascript;

    @NotBlank
    private String jsp;

    @NotBlank
    private String pojo;

    @NotBlank
    private String servicio;

    @NotBlank 
    private String repositorio;

    @NotBlank
    private String controlador;

    private List<String> apis;

}
