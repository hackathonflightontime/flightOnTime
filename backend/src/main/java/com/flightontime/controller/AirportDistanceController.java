package com.flightontime.controller;

import com.flightontime.dto.AirportDistanceResponse;
import com.flightontime.exception.ResourceNotFoundException;
import com.flightontime.service.AirportDistanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AirportDistanceController {

    private final AirportDistanceService service;

    public AirportDistanceController(AirportDistanceService service) {
        this.service = service;
    }

    @Operation(
        summary = "Calcula distância entre aeroportos",
        description = "Retorna a distância em quilômetros entre dois aeroportos informados pelos códigos IATA."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Distância retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aeroporto não encontrado ou distância indisponível")
    })
    @GetMapping("/airports/distance")
    public ResponseEntity<AirportDistanceResponse> getDistance(

            @Parameter(description = "Código IATA do aeroporto de origem", example = "GIG", required = true)
            @RequestParam String origem,

            @Parameter(description = "Código IATA do aeroporto de destino", example = "GRU", required = true)
            @RequestParam String destino
    ) {
        return service.buscarDistancia(origem, destino)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Distância não encontrada para origem=" + origem + " e destino=" + destino
                ));
    }
}