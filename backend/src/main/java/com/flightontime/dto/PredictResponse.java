package com.flightontime.dto;

public record PredictResponse(
        String previsao,
        Double probabilidade
) {}