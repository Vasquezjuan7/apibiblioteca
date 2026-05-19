package com.biblioteca.controller;

import com.biblioteca.dto.UsuarioRequest;
import com.biblioteca.dto.UsuarioResponse;
import com.biblioteca.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> createUsuario(@RequestBody UsuarioRequest request) {
        return new ResponseEntity<>(usuarioService.createUsuario(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> getUsuarioById(@PathVariable String id) {
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> updateUsuario(@PathVariable String id, @RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(usuarioService.updateUsuario(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
