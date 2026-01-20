package com.flightontime.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PredictModelResponse(
        @JsonProperty("previsao") String prediction,
        @JsonProperty("probabilidade") Double probability
) {}