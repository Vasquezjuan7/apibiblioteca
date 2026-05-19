package com.biblioteca.controller;

import com.biblioteca.dto.EjemplarRequest;
import com.biblioteca.dto.EjemplarResponse;
import com.biblioteca.service.EjemplarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ejemplares")
public class EjemplarController {

    private final EjemplarService ejemplarService;

    public EjemplarController(EjemplarService ejemplarService) {
        this.ejemplarService = ejemplarService;
    }

    @PostMapping
    public ResponseEntity<EjemplarResponse> createEjemplar(@RequestBody EjemplarRequest request) {
        return new ResponseEntity<>(ejemplarService.createEjemplar(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EjemplarResponse>> getAllEjemplares() {
        return ResponseEntity.ok(ejemplarService.getAllEjemplares());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EjemplarResponse> getEjemplarById(@PathVariable String id) {
        return ResponseEntity.ok(ejemplarService.getEjemplarById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EjemplarResponse> updateEjemplar(@PathVariable String id, @RequestBody EjemplarRequest request) {
        return ResponseEntity.ok(ejemplarService.updateEjemplar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEjemplar(@PathVariable String id) {
        ejemplarService.deleteEjemplar(id);
        return ResponseEntity.noContent().build();
    }
}
