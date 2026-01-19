package com.flightontime.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    description = "Dados de uma companhia aérea",
    example = """
    {
      "iataCode": "AA",
      "name": "American Airlines"
    }
    """
)
public record CompanyResponse(

        @Schema(
            description = "Código IATA da companhia aérea",
            example = "AA"
        )
        String iataCode,

        @Schema(
            description = "Nome da companhia aérea",
            example = "American Airlines"
        )
        String name
) {}