package com.example.ejercicio12.mappers;

import com.example.ejercicio12.dtos.request.LogRequestDTO;
import com.example.ejercicio12.dtos.response.LogResponseDTO;
import com.example.ejercicio12.entidades.LogClase;

public class MapperLog {

    public static LogClase toEntity(LogRequestDTO dto) {

        LogClase log = new LogClase();

        log.setMessage(dto.getMessage());
        log.setLogLevel(dto.getLogLevel());

        return log;
    }

    public static LogResponseDTO toDTO(LogClase log) {

        LogResponseDTO dto = new LogResponseDTO();

        dto.setId(log.getId());
        dto.setMessage(log.getMessage());
        dto.setLogLevel(log.getLogLevel());
        dto.setTimestamp(log.getTimestamp());
        dto.setApplicationId(log.getApplication().getId());

        return dto;
    }

}
