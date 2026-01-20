package com.flightontime.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Dados necessários para previsão de atraso de um voo", example = """
        {
          "companhia": "AA",
          "origem": "ABQ",
          "destino": "ATL",
          "estado_origem": "New Mexico",
          "estado_destino": "Georgia",
          "distancia_km": 2040,
          "data_partida": "2026-01-15T10:00:00"
        }
        """)
public class VooRequest {

    @NotBlank(message = "Companhia é obrigatória")
    @Schema(description = "Código da companhia aérea", example = "AA")
    @JsonProperty("companhia")
    private String company;

    @NotBlank(message = "Aeroporto de origem é obrigatório")
    @Schema(description = "Código IATA do aeroporto de origem", example = "ABQ")
    @JsonProperty("origem")
    private String origin;

    @NotBlank(message = "Aeroporto de destino é obrigatório")
    @Schema(description = "Código IATA do aeroporto de destino", example = "ATL")
    @JsonProperty("destino")
    private String destination;

    @NotNull(message = "Data da Partida é obrigatória")
    @Schema(description = "Data e hora previstas para a partida do voo", example = "2026-01-15T10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("data_partida")
    private LocalDateTime departureDate;

    @Min(value = 1, message = "Distância deve ser >= 1")
    @Schema(description = "Distância do voo em quilômetros", example = "2040")
    @JsonProperty("distancia_km")
    private int distanceKm;

    @NotBlank(message = "Falta Estado de Origem")
    @Schema(description = "Estado de origem do voo", example = "New Mexico")
    @JsonProperty("estado_origem")
    private String stateOrigin;

    @NotBlank(message = "Falta Estado de Destino")
    @Schema(description = "Estado de destino do voo", example = "Georgia")
    @JsonProperty("estado_destino")
    private String stateDestination;
}