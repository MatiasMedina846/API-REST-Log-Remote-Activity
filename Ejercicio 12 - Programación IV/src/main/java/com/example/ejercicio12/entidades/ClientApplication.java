package com.example.ejercicio12.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ClientApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String responsibleEmail;

    @Column(unique = true, nullable = false)
    private UUID apiKey;

    @OneToMany(mappedBy = "application")
    private List<LogClase> logs = new ArrayList<>();
}
