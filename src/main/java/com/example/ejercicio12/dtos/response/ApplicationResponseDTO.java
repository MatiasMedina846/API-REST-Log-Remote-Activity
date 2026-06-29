package com.example.ejercicio12.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ApplicationResponseDTO {

    private Long id;

    private String name;

    private String description;

    private String responsibleEmail;

    private UUID apiKey;
}
