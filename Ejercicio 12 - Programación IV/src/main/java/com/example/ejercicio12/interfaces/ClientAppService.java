package com.example.ejercicio12.interfaces;

import com.example.ejercicio12.dtos.request.ApplicationRequestDTO;
import com.example.ejercicio12.dtos.response.ApplicationResponseDTO;

import java.util.List;

public interface ClientAppService {

    ApplicationResponseDTO create(
            ApplicationRequestDTO dto
    );

    List<ApplicationResponseDTO> findAll();
}
