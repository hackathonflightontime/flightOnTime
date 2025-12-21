package com.flightontime.controller;

import com.flightontime.service.PredictionService;
import jakarta.validation.Valid;
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

    private final PredictionService predictionService;

    public PredictController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping
    public ResponseEntity<PredictResponse> predict(
            @Valid @RequestBody PredictRequest request) {

        PredictResponse response = predictionService.predict(request);
        return ResponseEntity.ok(response);
    }
}