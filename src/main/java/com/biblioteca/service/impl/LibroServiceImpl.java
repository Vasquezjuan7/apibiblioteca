package com.biblioteca.service.impl;

import com.biblioteca.dto.LibroRequest;
import com.biblioteca.dto.LibroResponse;
import com.biblioteca.model.Libro;
import com.biblioteca.repository.LibroRepository;
import com.biblioteca.service.LibroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public LibroResponse createLibro(LibroRequest request) {
        Libro libro = new Libro();
        libro.setIsbn(request.getIsbn());
        libro.setTitulo(request.getTitulo());
        libro.setAutor(request.getAutor());
        libro.setAnioPublicacion(request.getAnioPublicacion());
        libro.setCategoria(request.getCategoria());

        Libro saved = libroRepository.save(libro);
        return mapToResponse(saved);
    }

    @Override
    public List<LibroResponse> getAllLibros() {
        return libroRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LibroResponse getLibroById(String id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        return mapToResponse(libro);
    }

    @Override
    public LibroResponse updateLibro(String id, LibroRequest request) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        
        libro.setIsbn(request.getIsbn());
        libro.setTitulo(request.getTitulo());
        libro.setAutor(request.getAutor());
        libro.setAnioPublicacion(request.getAnioPublicacion());
        libro.setCategoria(request.getCategoria());

        Libro updated = libroRepository.save(libro);
        return mapToResponse(updated);
    }

    @Override
    public void deleteLibro(String id) {
        if (!libroRepository.existsById(id)) {
            throw new RuntimeException("Libro no encontrado");
        }
        libroRepository.deleteById(id);
    }

    private LibroResponse mapToResponse(Libro libro) {
        LibroResponse response = new LibroResponse();
        response.setId(libro.getId());
        response.setIsbn(libro.getIsbn());
        response.setTitulo(libro.getTitulo());
        response.setAutor(libro.getAutor());
        response.setAnioPublicacion(libro.getAnioPublicacion());
        response.setCategoria(libro.getCategoria());
        return response;
    }
}
