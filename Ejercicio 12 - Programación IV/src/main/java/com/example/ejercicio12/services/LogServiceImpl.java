package com.example.ejercicio12.services;

import com.example.ejercicio12.dtos.request.LogRequestDTO;
import com.example.ejercicio12.dtos.response.LogResponseDTO;
import com.example.ejercicio12.entidades.ClientApplication;
import com.example.ejercicio12.entidades.LogClase;
import com.example.ejercicio12.exceptions.InvalidApiKeyException;
import com.example.ejercicio12.interfaces.LogService;
import com.example.ejercicio12.mappers.MapperLog;
import com.example.ejercicio12.repositories.ClientApplicationRepository;
import com.example.ejercicio12.repositories.LogClaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LogServiceImpl implements LogService {

    private final LogClaseRepository logRepository;

    private final ClientApplicationRepository appRepository;

    public LogServiceImpl(
            LogClaseRepository logRepository,
            ClientApplicationRepository appRepository
    ) {
        this.logRepository = logRepository;
        this.appRepository = appRepository;
    }

    @Override
    public LogResponseDTO create(
            LogRequestDTO dto,
            UUID apiKey
    ) {

        ClientApplication app =
                appRepository
                        .findByIdAndApiKey(
                                dto.getAppId(),
                                apiKey
                        )
                        .orElseThrow(
                                InvalidApiKeyException::new
                        );

        LogClase log = new LogClase();

        log.setMessage(dto.getMessage());
        log.setLogLevel(dto.getLogLevel());
        log.setApplication(app);
        log.setTimestamp(LocalDateTime.now());

        logRepository.save(log);

        return MapperLog.toDTO(log);
    }

    @Override
    public List<LogResponseDTO> findLogs(
            Long appId,
            LocalDateTime from,
            LocalDateTime to
    ) {

        List<LogClase> logs;

        if (appId != null && from != null && to != null) {

            logs = logRepository
                    .findByApplicationIdAndTimestampBetween(
                            appId,
                            from,
                            to
                    );

        } else if (appId != null) {

            logs = logRepository
                    .findByApplicationId(appId);

        } else if (from != null && to != null) {

            logs = logRepository
                    .findByTimestampBetween(from, to);

        } else {

            logs = logRepository.findAll();
        }

        return logs.stream()
                .map(MapperLog::toDTO)
                .toList();
    }
}
