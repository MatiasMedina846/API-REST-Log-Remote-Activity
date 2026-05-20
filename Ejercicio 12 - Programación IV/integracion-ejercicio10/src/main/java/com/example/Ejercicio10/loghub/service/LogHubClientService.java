package com.example.Ejercicio10.loghub.service;

import com.example.Ejercicio10.loghub.dto.LogHubLogRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class LogHubClientService {

    private final RestTemplate restTemplate;

    @Value("${loghub.base-url}")
    private String logHubBaseUrl;

    @Value("${loghub.app-id}")
    private Long appId;

    @Value("${loghub.api-key}")
    private String apiKey;

    public LogHubClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public void registerRequestLog(String method, String path, int statusCode) {
        String message = "HTTP " + method + " " + path + " -> " + statusCode;
        LogHubLogRequest request = new LogHubLogRequest(message, resolveLogLevel(statusCode), appId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", apiKey);

        HttpEntity<LogHubLogRequest> entity = new HttpEntity<>(request, headers);

        try {
            restTemplate.postForEntity(logHubBaseUrl + "/api/logs", entity, Void.class);
        } catch (RestClientException ignored) {
            // No se debe romper la respuesta del proyecto cliente si LogHub no responde.
        }
    }

    private String resolveLogLevel(int statusCode) {
        if (statusCode >= 500) {
            return "ERROR";
        }
        if (statusCode >= 400) {
            return "WARNING";
        }
        return "INFO";
    }
}
