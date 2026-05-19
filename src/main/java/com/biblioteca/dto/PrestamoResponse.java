package com.biblioteca.dto;

import lombok.Data;
import java.util.Date;

@Data
public class PrestamoResponse {
    private String id;
    private String usuarioId;
    private String ejemplarId;
    private Date fechaPrestamo;
    private Date fechaDevolucionEsperada;
    private Date fechaDevolucionReal;
    private String estado;
}
