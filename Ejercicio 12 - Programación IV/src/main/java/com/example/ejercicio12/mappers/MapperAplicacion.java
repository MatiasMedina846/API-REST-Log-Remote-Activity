package com.example.ejercicio12.mappers;

import com.example.ejercicio12.dtos.request.ApplicationRequestDTO;
import com.example.ejercicio12.dtos.response.ApplicationResponseDTO;
import com.example.ejercicio12.entidades.ClientApplication;

import java.util.UUID;

public class MapperAplicacion {

    public static ClientApplication toEntity(ApplicationRequestDTO dto) {

        ClientApplication app = new ClientApplication();

        app.setName(dto.getName());
        app.setDescription(dto.getDescription());
        app.setResponsibleEmail(dto.getResponsibleEmail());
        app.setApiKey(UUID.randomUUID());

        return app;
    }

    public static ApplicationResponseDTO toDTO(ClientApplication app) {

        ApplicationResponseDTO dto = new ApplicationResponseDTO();

        dto.setId(app.getId());
        dto.setName(app.getName());
        dto.setDescription(app.getDescription());
        dto.setResponsibleEmail(app.getResponsibleEmail());
        dto.setApiKey(app.getApiKey());

        return dto;
    }
}
