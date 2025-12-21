package com.flightontime.service;

import com.flightontime.dto.PredictRequest;
import com.flightontime.dto.PredictResponse;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class PredictionService {

    private final Random random = new Random();

    public PredictResponse predict(PredictRequest request) {

        double probabilidade = Math.round(random.nextDouble() * 100.0) / 100.0;
        String previsao = probabilidade > 0.5 ? "Atrasado" : "Pontual";

        return new PredictResponse(previsao, probabilidade);
    }
}
