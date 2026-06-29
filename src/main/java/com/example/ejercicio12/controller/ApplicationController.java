package com.example.ejercicio12.controller;

import com.example.ejercicio12.dtos.request.ApplicationRequestDTO;
import com.example.ejercicio12.dtos.response.ApplicationResponseDTO;
import com.example.ejercicio12.interfaces.ClientAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@Tag(name = "Applications", description = "Endpoints for managing client applications")
public class ApplicationController {

    private final ClientAppService service;

    public ApplicationController(ClientAppService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Register a new application", description = "Creates a new application and generates a unique API Key")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Application created successfully",
            content = @Content(schema = @Schema(implementation = ApplicationResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Validation error")
    })
    public ResponseEntity<ApplicationResponseDTO> create(@Valid @RequestBody ApplicationRequestDTO dto) {
        ApplicationResponseDTO response = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "List all applications", description = "Returns all registered applications")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of applications",
            content = @Content(schema = @Schema(implementation = ApplicationResponseDTO.class)))
    })
    public ResponseEntity<List<ApplicationResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
