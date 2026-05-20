package com.example.ejercicio12.repositories;

import com.example.ejercicio12.entidades.LogClase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LogClaseRepository extends JpaRepository<LogClase, Long> {

    List<LogClase> findByApplicationId(Long appId);

    List<LogClase> findByTimestampBetween(
            LocalDateTime from,
            LocalDateTime to
    );

    List<LogClase> findByApplicationIdAndTimestampBetween(
            Long appId,
            LocalDateTime from,
            LocalDateTime to
    );

}
