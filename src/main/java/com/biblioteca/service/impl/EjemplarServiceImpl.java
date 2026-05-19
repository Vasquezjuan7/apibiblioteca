package com.biblioteca.service.impl;

import com.biblioteca.dto.EjemplarRequest;
import com.biblioteca.dto.EjemplarResponse;
import com.biblioteca.model.Ejemplar;
import com.biblioteca.repository.EjemplarRepository;
import com.biblioteca.service.EjemplarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EjemplarServiceImpl implements EjemplarService {

    private final EjemplarRepository ejemplarRepository;

    public EjemplarServiceImpl(EjemplarRepository ejemplarRepository) {
        this.ejemplarRepository = ejemplarRepository;
    }

    @Override
    public EjemplarResponse createEjemplar(EjemplarRequest request) {
        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setLibroId(request.getLibroId());
        ejemplar.setCodigoEjemplar(request.getCodigoEjemplar());
        ejemplar.setEstado(request.getEstado());
        ejemplar.setUbicacion(request.getUbicacion());

        Ejemplar saved = ejemplarRepository.save(ejemplar);
        return mapToResponse(saved);
    }

    @Override
    public List<EjemplarResponse> getAllEjemplares() {
        return ejemplarRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EjemplarResponse getEjemplarById(String id) {
        Ejemplar ejemplar = ejemplarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado"));
        return mapToResponse(ejemplar);
    }

    @Override
    public EjemplarResponse updateEjemplar(String id, EjemplarRequest request) {
        Ejemplar ejemplar = ejemplarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado"));
        
        ejemplar.setLibroId(request.getLibroId());
        ejemplar.setCodigoEjemplar(request.getCodigoEjemplar());
        ejemplar.setEstado(request.getEstado());
        ejemplar.setUbicacion(request.getUbicacion());

        Ejemplar updated = ejemplarRepository.save(ejemplar);
        return mapToResponse(updated);
    }

    @Override
    public void deleteEjemplar(String id) {
        if (!ejemplarRepository.existsById(id)) {
            throw new RuntimeException("Ejemplar no encontrado");
        }
        ejemplarRepository.deleteById(id);
    }

    private EjemplarResponse mapToResponse(Ejemplar ejemplar) {
        EjemplarResponse response = new EjemplarResponse();
        response.setId(ejemplar.getId());
        response.setLibroId(ejemplar.getLibroId());
        response.setCodigoEjemplar(ejemplar.getCodigoEjemplar());
        response.setEstado(ejemplar.getEstado());
        response.setUbicacion(ejemplar.getUbicacion());
        return response;
    }
}
