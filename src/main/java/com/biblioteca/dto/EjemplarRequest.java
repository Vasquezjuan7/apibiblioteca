package com.biblioteca.dto;

import lombok.Data;

@Data
public class EjemplarRequest {
    private String libroId;
    private String codigoEjemplar;
    private String estado;
    private String ubicacion;
}
