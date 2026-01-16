package com.flightontime.controller;

import com.flightontime.dto.CompanyResponse;
import com.flightontime.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")

public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }
    @Operation(
        summary = "Lista companhias aéreas", 
        description = "Retorna a lista de companhias aéreas disponíveis para seleção."
    )

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Lista retornada com sucesso", 
            content = @Content(
                mediaType = "application/json", 
                schema = @Schema(implementation = CompanyResponse.class, type = "array"), 
                examples = @ExampleObject(
                    value = "[{\"id\":1,\"codigo\":\"AA\",\"nome\":\"American Airlines\"},{\"id\":2,\"codigo\":\"DL\",\"nome\":\"Delta Air Lines\"}]"
                )
            )
        )
    })

    @GetMapping
    public List<CompanyResponse> listarTodas() {
        return service.listarTodas();
    }
}