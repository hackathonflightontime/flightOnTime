package com.flightontime.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    description = "Distância entre dois aeroportos",
    example = """
    {
      "iataCodeOrigin": "GIG",
      "iataCodeDest": "GRU",
      "distance": 340
    }
    """
)
public record AirportDistanceResponse(

        @Schema(
            description = "Código IATA do aeroporto de origem",
            example = "GIG"
        )
        String iataCodeOrigin,

        @Schema(
            description = "Código IATA do aeroporto de destino",
            example = "GRU"
        )
        String iataCodeDest,

        @Schema(
            description = "Distância entre os aeroportos em quilômetros",
            example = "340"
        )
        Integer distance
) {}