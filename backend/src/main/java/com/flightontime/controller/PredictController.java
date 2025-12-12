package com.flightontime.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightontime.dto.PredictRequest;
import com.flightontime.dto.PredictResponse;

@RestController
@RequestMapping("/predict")
public class PredictController {

    @PostMapping
    public ResponseEntity<PredictResponse> predict(@RequestBody PredictRequest request) {
        // Por enquanto, resposta fixa só para validar o fluxo.
        PredictResponse response = new PredictResponse("pendente", 0.0);
        return ResponseEntity.ok(response);
    }
}