package com.flightontime.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class VooResponse {
    private String previsao;
    private double probabilidade;

    public VooResponse(String previsao, double probabilidade) {
        this.previsao = previsao;
        this.probabilidade = probabilidade;
    }
}