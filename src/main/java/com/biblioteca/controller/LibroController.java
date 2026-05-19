package com.biblioteca.controller;

import com.biblioteca.dto.LibroRequest;
import com.biblioteca.dto.LibroResponse;
import com.biblioteca.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<LibroResponse> createLibro(@RequestBody LibroRequest request) {
        return new ResponseEntity<>(libroService.createLibro(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LibroResponse>> getAllLibros() {
        return ResponseEntity.ok(libroService.getAllLibros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroResponse> getLibroById(@PathVariable String id) {
        return ResponseEntity.ok(libroService.getLibroById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroResponse> updateLibro(@PathVariable String id, @RequestBody LibroRequest request) {
        return ResponseEntity.ok(libroService.updateLibro(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable String id) {
        libroService.deleteLibro(id);
        return ResponseEntity.noContent().build();
    }
}
