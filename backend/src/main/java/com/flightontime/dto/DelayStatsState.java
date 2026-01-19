package com.flightontime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    description = "Estatísticas de atraso agrupadas por estado",
    example = """
    {
      "state": "Georgia",
      "totalFlights": 120,
      "average": 0.42,
      "min": 0.05,
      "max": 0.91,
      "stdDev": 0.18
    }
    """
)
public class DelayStatsState {

    @Schema(description = "Estado analisado", example = "Georgia")
    private String state;

    @Schema(description = "Total de voos analisados no estado", example = "120")
    private long totalFlights;

    @Schema(description = "Probabilidade média de atraso", example = "0.42")
    private double average;

    @Schema(description = "Menor probabilidade de atraso", example = "0.05")
    private double min;

    @Schema(description = "Maior probabilidade de atraso", example = "0.91")
    private double max;

    @Schema(description = "Desvio padrão das probabilidades", example = "0.18")
    private double stdDev;
}