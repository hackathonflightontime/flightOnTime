package com.flightontime.controller;

import com.flightontime.dto.DelayStatsResponse;
import com.flightontime.dto.DelayStatsState;
import com.flightontime.service.DelayStatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/stats")
public class DelayStatsController {

    private final DelayStatsService service;

    public DelayStatsController(DelayStatsService service) {
        this.service = service;
    }

    @Operation(
        summary = "Estatísticas gerais de atrasos",
        description = "Retorna estatísticas agregadas de atrasos de voos em um período informado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estatísticas calculadas com sucesso"),
        @ApiResponse(responseCode = "400", description = "Parâmetros de data inválidos")
    })
    @GetMapping
    public DelayStatsResponse calcularEstatisticas(
            @Parameter(
                description = "Data inicial do período (ISO 8601)",
                example = "2026-01-01T00:00:00",
                required = true
            )
            @RequestParam("start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime start,

            @Parameter(
                description = "Data final do período (ISO 8601)",
                example = "2026-01-31T23:59:59",
                required = true
            )
            @RequestParam("end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime end
    ) {
        return service.calcularEstatisticasPeriodo(start, end);
    }

    @Operation(
        summary = "Estatísticas de atrasos por estado",
        description = "Retorna estatísticas de atrasos agrupadas por estado em um período informado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estatísticas por estado retornadas com sucesso"),
        @ApiResponse(responseCode = "400", description = "Parâmetros de data inválidos")
    })
    @GetMapping("/estado")
    public List<DelayStatsState> getStatsPorEstado(
            @Parameter(
                description = "Data inicial do período (ISO 8601)",
                example = "2026-01-01T00:00:00",
                required = true
            )
            @RequestParam("start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime start,

            @Parameter(
                description = "Data final do período (ISO 8601)",
                example = "2026-01-31T23:59:59",
                required = true
            )
            @RequestParam("end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime end
    ) {
        return service.calcularPorEstado(start, end);
    }
}
