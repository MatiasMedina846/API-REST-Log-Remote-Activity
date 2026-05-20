package com.example.ejercicio12.services;

import com.example.ejercicio12.dtos.request.ApplicationRequestDTO;
import com.example.ejercicio12.dtos.response.ApplicationResponseDTO;
import com.example.ejercicio12.entidades.ClientApplication;
import com.example.ejercicio12.interfaces.ClientAppService;
import com.example.ejercicio12.mappers.MapperAplicacion;
import com.example.ejercicio12.repositories.ClientApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientApplicationServiceImpl
        implements ClientAppService {

    private final ClientApplicationRepository repository;

    public ClientApplicationServiceImpl(
            ClientApplicationRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public ApplicationResponseDTO create(
            ApplicationRequestDTO dto
    ) {

        ClientApplication app =
                MapperAplicacion.toEntity(dto);

        repository.save(app);

        return MapperAplicacion.toDTO(app);
    }

    @Override
    public List<ApplicationResponseDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(MapperAplicacion::toDTO)
                .toList();
    }
}
