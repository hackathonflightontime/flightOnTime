package com.flightontime.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class VooRequest {

    @NotBlank(message = "companhia é obrigatória")
    private String companhia;

    @NotBlank(message = "origem é obrigatória")
    private String origem;

    @NotBlank(message = "destino é obrigatória")
    private String destino;

    @NotBlank(message = "data_partida é obrigatória")
    private String data_partida; // formato ISO 8601

    @Min(value = 1, message = "distancia_km deve ser >= 1")
    private int distancia_km;

    public String getCompanhia() {
        return companhia;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public String getData_partida() {
        return data_partida;
    }

    public int getDistancia_km() {
        return distancia_km;
    }
}