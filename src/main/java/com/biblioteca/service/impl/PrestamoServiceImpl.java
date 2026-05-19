package com.biblioteca.service.impl;

import com.biblioteca.dto.PrestamoRequest;
import com.biblioteca.dto.PrestamoResponse;
import com.biblioteca.model.Ejemplar;
import com.biblioteca.model.Prestamo;
import com.biblioteca.model.Usuario;
import com.biblioteca.repository.EjemplarRepository;
import com.biblioteca.repository.PrestamoRepository;
import com.biblioteca.repository.UsuarioRepository;
import com.biblioteca.service.PrestamoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EjemplarRepository ejemplarRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository, 
                               UsuarioRepository usuarioRepository, 
                               EjemplarRepository ejemplarRepository) {
        this.prestamoRepository = prestamoRepository;
        this.usuarioRepository = usuarioRepository;
        this.ejemplarRepository = ejemplarRepository;
    }

    @Override
    public PrestamoResponse createPrestamo(PrestamoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        Ejemplar ejemplar = ejemplarRepository.findById(request.getEjemplarId())
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado"));
        
        if (!"DISPONIBLE".equalsIgnoreCase(ejemplar.getEstado())) {
            throw new RuntimeException("El ejemplar no se encuentra DISPONIBLE para préstamo");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuarioId(usuario.getId());
        prestamo.setEjemplarId(ejemplar.getId());
        prestamo.setFechaPrestamo(new Date());
        prestamo.setFechaDevolucionEsperada(request.getFechaDevolucionEsperada());
        prestamo.setEstado("ACTIVO");

        ejemplar.setEstado("PRESTADO");
        ejemplarRepository.save(ejemplar);

        Prestamo saved = prestamoRepository.save(prestamo);
        return mapToResponse(saved);
    }

    @Override
    public List<PrestamoResponse> getAllPrestamos() {
        return prestamoRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PrestamoResponse getPrestamoById(String id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));
        return mapToResponse(prestamo);
    }

    @Override
    public PrestamoResponse updatePrestamo(String id, PrestamoRequest request) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));
        
        prestamo.setUsuarioId(request.getUsuarioId());
        prestamo.setEjemplarId(request.getEjemplarId());
        prestamo.setFechaDevolucionEsperada(request.getFechaDevolucionEsperada());
        
        Prestamo updated = prestamoRepository.save(prestamo);
        return mapToResponse(updated);
    }

    @Override
    public void deletePrestamo(String id) {
        if (!prestamoRepository.existsById(id)) {
            throw new RuntimeException("Préstamo no encontrado");
        }
        prestamoRepository.deleteById(id);
    }

    @Override
    public PrestamoResponse registrarDevolucion(String id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));
        
        prestamo.setFechaDevolucionReal(new Date());
        prestamo.setEstado("DEVUELTO");

        Ejemplar ejemplar = ejemplarRepository.findById(prestamo.getEjemplarId())
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado"));
        
        ejemplar.setEstado("DISPONIBLE");
        ejemplarRepository.save(ejemplar);

        Prestamo saved = prestamoRepository.save(prestamo);
        return mapToResponse(saved);
    }

    private PrestamoResponse mapToResponse(Prestamo prestamo) {
        PrestamoResponse response = new PrestamoResponse();
        response.setId(prestamo.getId());
        response.setUsuarioId(prestamo.getUsuarioId());
        response.setEjemplarId(prestamo.getEjemplarId());
        response.setFechaPrestamo(prestamo.getFechaPrestamo());
        response.setFechaDevolucionEsperada(prestamo.getFechaDevolucionEsperada());
        response.setFechaDevolucionReal(prestamo.getFechaDevolucionReal());
        response.setEstado(prestamo.getEstado());
        return response;
    }
}
