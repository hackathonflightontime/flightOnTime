package com.flightontime.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "voo")
@Getter
@Setter
@AllArgsConstructor
public class Voo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3, nullable = false)
    private String companhia; // código da cia (ex.: AZ)

    @Column(length = 3, nullable = false)
    private String origem;    // IATA (ex.: GIG)

    @Column(length = 3, nullable = false)
    private String destino;   // IATA (ex.: GRU)

    @Column(name = "data_partida", nullable = false)
    private LocalDateTime dataPartida;

    @Column(name = "distancia_km", nullable = false)
    private Integer distanciaKm;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Voo() {}

    public Voo(String companhia, String origem, String destino,
               LocalDateTime dataPartida, Integer distanciaKm) {
        this.companhia = companhia;
        this.origem = origem;
        this.destino = destino;
        this.dataPartida = dataPartida;
        this.distanciaKm = distanciaKm;
    }
}