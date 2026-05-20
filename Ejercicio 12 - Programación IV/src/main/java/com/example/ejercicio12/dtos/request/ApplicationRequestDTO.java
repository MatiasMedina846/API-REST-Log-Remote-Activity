package com.example.ejercicio12.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Email
    @NotBlank
    private String responsibleEmail;
}
