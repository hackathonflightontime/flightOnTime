package com.flightontime.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flightontime.dto.VooRequest;
import com.flightontime.dto.VooResponse;
import com.flightontime.service.VooService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/predict")
public class VooController {

    private final VooService vooService;

    public VooController(VooService vooService) {
        this.vooService = vooService;
    }

    @Operation(summary = "Prediz atraso do voo", description = "Retorna previsão (Pontual/Atrasado) e probabilidade calculada pelo modelo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Predição realizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VooResponse.class), examples = @ExampleObject(value = "{\"previsao\":\"Pontual\",\"probabilidade\":0.18571779002989486}"))),
            @ApiResponse(responseCode = "400", description = "Erro de validação", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"timestamp\":\"2026-01-16T01:02:40.364101825\",\"status\":400,\"error\":\"Bad Request\",\"message\":\"Erro de validação\",\"path\":\"/predict\",\"fieldErrors\":{\"company\":\"Companhia é obrigatória\"}}"))),
            @ApiResponse(responseCode = "503", description = "Serviço de predição indisponível", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"timestamp\":\"2026-01-16T08:42:13.454333302\",\"status\":503,\"error\":\"Service Unavailable\",\"message\":\"Serviço de predição indisponível\",\"path\":\"/predict\"}")))
    })

    @PostMapping
    public ResponseEntity<VooResponse> predict(
            @Valid @RequestBody VooRequest request) {
        return ResponseEntity.ok(vooService.predict(request));
    }
}