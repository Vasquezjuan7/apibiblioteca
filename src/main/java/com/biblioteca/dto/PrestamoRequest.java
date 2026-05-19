package com.biblioteca.dto;

import lombok.Data;
import java.util.Date;

@Data
public class PrestamoRequest {
    private String usuarioId;
    private String ejemplarId;
    private Date fechaDevolucionEsperada;
}
