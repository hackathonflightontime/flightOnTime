package com.flightontime.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de um aeroporto", example = """
                {
                  "iataCode": "ABQ",
                  "airportName": "Albuquerque International Sunport",
                  "cityName": "Albuquerque",
                  "stateName": "New Mexico"
                }
                """)
public record AirportResponse(

                @Schema(description = "Código IATA do aeroporto", example = "ABQ") String iataCode,

                @Schema(description = "Nome do aeroporto", example = "Albuquerque International Sunport") String airportName,

                @Schema(description = "Cidade onde o aeroporto está localizado", example = "Albuquerque") String cityName,

                @Schema(description = "Estado onde o aeroporto está localizado", example = "New Mexico") String stateName) {
}