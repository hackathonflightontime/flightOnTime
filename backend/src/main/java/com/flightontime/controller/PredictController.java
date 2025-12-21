package com.flightontime.controller;

import com.flightontime.dto.PredictRequest;
import com.flightontime.dto.PredictResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictController {

    @PostMapping("/predict")
    public PredictResponse predict(@RequestBody PredictRequest request) {
        return new PredictResponse(true, 0.85);
    }
}
