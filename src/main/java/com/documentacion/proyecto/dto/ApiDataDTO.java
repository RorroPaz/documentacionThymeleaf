package com.documentacion.proyecto.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiDataDTO {
    private String nombreApi;
    private String tipo; //GET , POST, PUT, DELETE
    private String ruta;
    private String datosRegresa;
    private String parametros;
    private String descripcion;
    private String comentarios;
}
