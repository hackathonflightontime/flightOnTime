package com.flightontime.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Resultado da previsão de atraso do voo", example = """
        {
          "previsao": "Pontual",
          "probabilidade": 0.18
        }
        """)

public class VooResponse {
    @Schema(description = "Classificação da previsão do voo", example = "Pontual")
    @JsonProperty("previsao")
    private String prediction;

    @Schema(description = "Probabilidade associada à previsão (0 a 1)", example = "0.18")
    @JsonProperty("probabilidade")
    private double probability;
}