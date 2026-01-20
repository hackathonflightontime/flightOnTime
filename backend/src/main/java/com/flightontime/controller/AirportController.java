package com.flightontime.controller;

import com.flightontime.dto.AirportResponse;
import com.flightontime.service.AirportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airports")

public class AirportController {

    private final AirportService service;

    public AirportController(AirportService service) {
        this.service = service;
    }

    @Operation(summary = "Lista aeroportos", description = "Retorna a lista de aeroportos disponíveis para seleção.")
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "200", 
                description = "Lista retornada com sucesso", 
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = AirportResponse.class, type = "array"), 
                examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "[{\"codigo\":\"ABQ\",\"nome\":\"Albuquerque International Sunport\",\"cidade\":\"Albuquerque\",\"estado\":\"New Mexico\"},{\"codigo\":\"ATL\",\"nome\":\"Hartsfield-Jackson Atlanta International Airport\",\"cidade\":\"Atlanta\",\"estado\":\"Georgia\"}]")))
    })

    @GetMapping
    public List<AirportResponse> listarTodos() {
        return service.listarTodos();
    }
}
