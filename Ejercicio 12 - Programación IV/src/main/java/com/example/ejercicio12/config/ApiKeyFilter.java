package com.example.ejercicio12.config;

import com.example.ejercicio12.repositories.ClientApplicationRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private final ClientApplicationRepository appRepository;

    public ApiKeyFilter(ClientApplicationRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        if ("POST".equalsIgnoreCase(request.getMethod()) && "/api/logs".equals(path)) {
            String apiKeyHeader = request.getHeader("X-API-KEY");

            if (apiKeyHeader == null || apiKeyHeader.isBlank()) {
                writeUnauthorizedProblem(response);
                return;
            }

            try {
                UUID apiKey = UUID.fromString(apiKeyHeader);
                if (appRepository.findByApiKey(apiKey).isEmpty()) {
                    writeUnauthorizedProblem(response);
                    return;
                }
            } catch (IllegalArgumentException e) {
                writeUnauthorizedProblem(response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void writeUnauthorizedProblem(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        response.getWriter().write("""
                {"type":"about:blank","title":"Unauthorized","status":401,"detail":"Invalid API Key"}
                """);
    }
}
