package com.biblioteca.service.impl;

import com.biblioteca.dto.UsuarioRequest;
import com.biblioteca.dto.UsuarioResponse;
import com.biblioteca.model.Usuario;
import com.biblioteca.repository.UsuarioRepository;
import com.biblioteca.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioResponse createUsuario(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        mapToEntity(request, usuario);
        Usuario saved = usuarioRepository.save(usuario);
        return mapToResponse(saved);
    }

    @Override
    public List<UsuarioResponse> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponse getUsuarioById(String id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapToResponse(usuario);
    }

    @Override
    public UsuarioResponse updateUsuario(String id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        mapToEntity(request, usuario);
        Usuario updated = usuarioRepository.save(usuario);
        return mapToResponse(updated);
    }

    @Override
    public void deleteUsuario(String id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    private void mapToEntity(UsuarioRequest request, Usuario usuario) {
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setTipoUsuario(request.getTipoUsuario());
        usuario.setCodigoEstudiante(request.getCodigoEstudiante());
        usuario.setPrograma(request.getPrograma());
        usuario.setCodigoProfesor(request.getCodigoProfesor());
        usuario.setFacultad(request.getFacultad());
        usuario.setCodigoEmpleado(request.getCodigoEmpleado());
        usuario.setTurno(request.getTurno());
    }

    private UsuarioResponse mapToResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setCorreo(usuario.getCorreo());
        response.setTipoUsuario(usuario.getTipoUsuario());
        response.setCodigoEstudiante(usuario.getCodigoEstudiante());
        response.setPrograma(usuario.getPrograma());
        response.setCodigoProfesor(usuario.getCodigoProfesor());
        response.setFacultad(usuario.getFacultad());
        response.setCodigoEmpleado(usuario.getCodigoEmpleado());
        response.setTurno(usuario.getTurno());
        return response;
    }
}
