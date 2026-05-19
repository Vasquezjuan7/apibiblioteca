package com.biblioteca.controller;

import com.biblioteca.dto.PrestamoRequest;
import com.biblioteca.dto.PrestamoResponse;
import com.biblioteca.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<PrestamoResponse> createPrestamo(@RequestBody PrestamoRequest request) {
        return new ResponseEntity<>(prestamoService.createPrestamo(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PrestamoResponse>> getAllPrestamos() {
        return ResponseEntity.ok(prestamoService.getAllPrestamos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoResponse> getPrestamoById(@PathVariable String id) {
        return ResponseEntity.ok(prestamoService.getPrestamoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestamoResponse> updatePrestamo(@PathVariable String id, @RequestBody PrestamoRequest request) {
        return ResponseEntity.ok(prestamoService.updatePrestamo(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestamo(@PathVariable String id) {
        prestamoService.deletePrestamo(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/devolucion")
    public ResponseEntity<PrestamoResponse> registrarDevolucion(@PathVariable String id) {
        return ResponseEntity.ok(prestamoService.registrarDevolucion(id));
    }
}
