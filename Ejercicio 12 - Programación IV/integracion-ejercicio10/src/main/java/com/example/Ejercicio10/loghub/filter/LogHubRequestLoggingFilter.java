package com.example.Ejercicio10.loghub.filter;

import com.example.Ejercicio10.loghub.service.LogHubClientService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LogHubRequestLoggingFilter extends OncePerRequestFilter {

    private final LogHubClientService logHubClientService;

    public LogHubRequestLoggingFilter(LogHubClientService logHubClientService) {
        this.logHubClientService = logHubClientService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        int statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

        try {
            filterChain.doFilter(request, response);
            statusCode = response.getStatus();
        } catch (ServletException | IOException | RuntimeException ex) {
            statusCode = response.getStatus() >= 400
                    ? response.getStatus()
                    : HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            throw ex;
        } finally {
            logHubClientService.registerRequestLog(
                    request.getMethod(),
                    request.getRequestURI(),
                    statusCode
            );
        }
    }
}
