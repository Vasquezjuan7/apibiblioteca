package com.biblioteca.dto;

import lombok.Data;

@Data
public class LibroRequest {
    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String categoria;
}
