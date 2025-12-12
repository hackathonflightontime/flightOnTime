package com.flightontime.dto;

public record PredictRequest(
        String companhia,
        String origem,
        String destino,
        String data_partida,
        Integer distancia_km
) {}