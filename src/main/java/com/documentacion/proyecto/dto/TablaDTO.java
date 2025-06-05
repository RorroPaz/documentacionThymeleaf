package com.documentacion.proyecto.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TablaDTO {
    private String nombre;
    private List<String> columnas = new ArrayList<>(); // Para inicalizar el objeto y poderle dar valores
}
