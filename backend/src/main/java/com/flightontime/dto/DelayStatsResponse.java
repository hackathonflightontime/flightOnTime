package com.flightontime.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    description = "Estatísticas agregadas de atrasos de voos em um período",
    example = """
    {
      "start": "2026-01-01T00:00:00",
      "end": "2026-01-31T23:59:59",
      "totalVoos": 350,
      "media": 0.38,
      "min": 0.02,
      "max": 0.95,
      "desvio": 0.21,
      "resultados": [
        {
          "companhia": "AA",
          "media": 0.41
        }
      ]
    }
    """
)
public class DelayStatsResponse {

    @Schema(description = "Data inicial do período analisado", example = "2026-01-01T00:00:00")
    private LocalDateTime start;

    @Schema(description = "Data final do período analisado", example = "2026-01-31T23:59:59")
    private LocalDateTime end;

    @Schema(description = "Total de voos analisados no período", example = "350")
    private long totalVoos;

    @Schema(
        description = "Resultados agregados por critério (ex: companhia, aeroporto, etc.)",
        example = "[{\"companhia\":\"AA\",\"media\":0.41}]"
    )
    private List<Map<String, Object>> resultados;

    @Schema(description = "Probabilidade média de atraso no período", example = "0.38")
    private double media;

    @Schema(description = "Menor probabilidade de atraso encontrada", example = "0.02")
    private double min;

    @Schema(description = "Maior probabilidade de atraso encontrada", example = "0.95")
    private double max;

    @Schema(description = "Desvio padrão das probabilidades", example = "0.21")
    private double desvio;
}