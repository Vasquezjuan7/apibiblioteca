package com.biblioteca.service;

import com.biblioteca.dto.UsuarioRequest;
import com.biblioteca.dto.UsuarioResponse;

import java.util.List;

public interface UsuarioService {
    UsuarioResponse createUsuario(UsuarioRequest request);
    List<UsuarioResponse> getAllUsuarios();
    UsuarioResponse getUsuarioById(String id);
    UsuarioResponse updateUsuario(String id, UsuarioRequest request);
    void deleteUsuario(String id);
}
