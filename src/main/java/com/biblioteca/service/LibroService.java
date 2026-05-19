package com.biblioteca.service;

import com.biblioteca.dto.LibroRequest;
import com.biblioteca.dto.LibroResponse;

import java.util.List;

public interface LibroService {
    LibroResponse createLibro(LibroRequest request);
    List<LibroResponse> getAllLibros();
    LibroResponse getLibroById(String id);
    LibroResponse updateLibro(String id, LibroRequest request);
    void deleteLibro(String id);
}
