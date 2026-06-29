package com.example.ejercicio12.dtos.request;

import com.example.ejercicio12.entidades.LogLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogRequestDTO {

    @NotBlank
    private String message;

    @NotNull
    private LogLevel logLevel;

    @NotNull
    private Long appId;
}
