package com.flightontime.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record PredictRequest(

        @NotBlank
        String companhia,

        @NotBlank
        String origem,

        @NotBlank
        String destino,

        @NotNull
        @JsonProperty("data_partida")
        String dataPartida,

        @NotNull
        @Positive
        @JsonProperty("distancia_km")
        Integer distanciaKm

) {}