package com.example.ejercicio12.controller;

import com.example.ejercicio12.dtos.request.LogRequestDTO;
import com.example.ejercicio12.dtos.response.LogResponseDTO;
import com.example.ejercicio12.interfaces.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/logs")
@Tag(name = "Logs", description = "Endpoints for managing logs")
public class LogController {

    private final LogService service;

    public LogController(LogService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Register a new log", description = "Creates a log entry. Requires X-API-KEY header for authentication.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Log created successfully",
            content = @Content(schema = @Schema(implementation = LogResponseDTO.class))),
        @ApiResponse(responseCode = "401", description = "Invalid or missing API Key"),
        @ApiResponse(responseCode = "400", description = "Validation error")
    })
    public ResponseEntity<LogResponseDTO> create(
            @Valid @RequestBody LogRequestDTO dto,
            @RequestHeader("X-API-KEY") UUID apiKey) {
        LogResponseDTO response = service.create(dto, apiKey);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "List logs", description = "Returns logs with optional filters by application ID and date range")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of logs",
            content = @Content(schema = @Schema(implementation = LogResponseDTO.class)))
    })
    public ResponseEntity<List<LogResponseDTO>> findLogs(
            @RequestParam(required = false) Long appId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ResponseEntity.ok(service.findLogs(appId, from, to));
    }
}
