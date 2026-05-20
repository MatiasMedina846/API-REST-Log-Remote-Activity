package com.example.ejercicio12.interfaces;

import com.example.ejercicio12.dtos.request.LogRequestDTO;
import com.example.ejercicio12.dtos.response.LogResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface LogService {

    LogResponseDTO create(
            LogRequestDTO dto,
            UUID apiKey
    );

    List<LogResponseDTO> findLogs(
            Long appId,
            LocalDateTime from,
            LocalDateTime to
    );
}
