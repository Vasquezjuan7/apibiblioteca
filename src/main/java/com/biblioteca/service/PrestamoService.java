package com.biblioteca.service;

import com.biblioteca.dto.PrestamoRequest;
import com.biblioteca.dto.PrestamoResponse;

import java.util.List;

public interface PrestamoService {
    PrestamoResponse createPrestamo(PrestamoRequest request);
    List<PrestamoResponse> getAllPrestamos();
    PrestamoResponse getPrestamoById(String id);
    PrestamoResponse updatePrestamo(String id, PrestamoRequest request);
    void deletePrestamo(String id);
    PrestamoResponse registrarDevolucion(String id);
}
