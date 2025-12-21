package com.flightontime.service;

import com.flightontime.dto.VooRequest;
import com.flightontime.dto.VooResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Service
public class VooService {

    private final WebClient.Builder webClient;
    private final String microserviceUrl;

    public VooService(WebClient.Builder webClient,
                      @Value("${app.ms.url}") String microserviceUrl) {
        this.webClient = webClient;
        this.microserviceUrl = microserviceUrl;

    }

    public VooResponse prever(VooRequest request) {

        Double probabilidade = webClient.build()
                .post()
                .uri(microserviceUrl)

                .bodyValue(request)
                .retrieve()
                .bodyToMono(Double.class)
                .block();

        String previsao = probabilidade >= 0.5 ? "Atrasado" : "Pontual";

        return new VooResponse(previsao, probabilidade);
    }
}