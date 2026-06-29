package com.example.ejercicio12.repositories;

import com.example.ejercicio12.entidades.ClientApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientApplicationRepository extends JpaRepository<ClientApplication, Long> {

    Optional<ClientApplication> findByApiKey(UUID apiKey);
    Optional<ClientApplication> findByIdAndApiKey(Long id, UUID apiKey);
}
