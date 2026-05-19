package com.biblioteca.service;

import com.biblioteca.dto.EjemplarRequest;
import com.biblioteca.dto.EjemplarResponse;

import java.util.List;

public interface EjemplarService {
    EjemplarResponse createEjemplar(EjemplarRequest request);
    List<EjemplarResponse> getAllEjemplares();
    EjemplarResponse getEjemplarById(String id);
    EjemplarResponse updateEjemplar(String id, EjemplarRequest request);
    void deleteEjemplar(String id);
}
