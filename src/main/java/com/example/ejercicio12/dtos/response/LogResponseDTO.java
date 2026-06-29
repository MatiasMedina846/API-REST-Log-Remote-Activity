package com.example.ejercicio12.dtos.response;

import com.example.ejercicio12.entidades.LogLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LogResponseDTO {

    private Long id;

    private String message;

    private LogLevel logLevel;

    private LocalDateTime timestamp;

    private Long applicationId;
}
