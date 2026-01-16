package com.flightontime.service;

import com.flightontime.dto.VooRequest;
import com.flightontime.dto.VooResponse;
import com.flightontime.exception.PredictionServiceUnavailableException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
@Service
public class VooService {

    private final WebClient.Builder webClient;

    @Value("${fastapi.url}")
    private String fastApiUrl;

    @Value("${prediction.threshold}")
    private double threshold;

    public VooService(WebClient.Builder webClient) {
        this.webClient = webClient;
    }

    // Envia para Modelo
    public VooResponse predict(VooRequest request) {
        try {
            Double probability = webClient.build()
                    .post()
                    .uri(fastApiUrl)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(Double.class)
                    .timeout(java.time.Duration.ofSeconds(5))
                    .block();

            if (probability == null) {
                throw new PredictionServiceUnavailableException("Serviço de predição retornou vazio");
            }

            String prediction = probability >= threshold ? "Atrasado" : "Pontual";
            return new VooResponse(prediction, probability);

        } catch (Exception ex) {
            throw new PredictionServiceUnavailableException("Serviço de predição indisponível", ex);
        }
    }
}