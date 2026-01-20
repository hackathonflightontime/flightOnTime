package com.flightontime.service;

import com.flightontime.dto.VooRequest;
import com.flightontime.dto.VooResponse;
import com.flightontime.exception.PredictionServiceUnavailableException;
import com.flightontime.model.Voo;
import com.flightontime.repository.VooRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;

@Service
public class VooService {

    private final VooRepository vooRepository;
    private final WebClient webClient;

    @Value("${prediction.threshold}")
    private double threshold;

    public VooService(
            VooRepository vooRepository,
            WebClient.Builder webClientBuilder,
            @Value("${fastapi.url}") String fastApiUrl) {
        this.vooRepository = vooRepository;
        this.webClient = webClientBuilder.baseUrl(fastApiUrl).build();
    }

    // Envia para Modelo salva no banco
    public VooResponse predict(VooRequest request) {
        try {
            Double probability = webClient.post()
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(Double.class)
                    .timeout(Duration.ofSeconds(5))
                    .block();

            if (probability == null) {
                throw new PredictionServiceUnavailableException("Serviço de predição retornou vazio");
            }

            String prediction = probability >= threshold ? "Atrasado" : "Pontual";

            // Persistência
            Voo voo = new Voo();
            voo.setCompany(request.getCompany());
            voo.setOrigin(request.getOrigin());
            voo.setDestination(request.getDestination());
            voo.setDepartureDate(request.getDepartureDate());
            voo.setDistanceKM(request.getDistanceKm());
            voo.setStateOrigin(request.getStateOrigin());
            voo.setStateDestitnation(request.getStateDestination());

            voo.setProbability(probability);
            voo.setPrediction(prediction);

            vooRepository.save(voo);

            return new VooResponse(prediction, probability);

        } catch (WebClientResponseException ex) {
            // DS respondeu com status != 2xx
            throw new PredictionServiceUnavailableException("Erro ao chamar serviço de predição", ex);
        } catch (Exception ex) {
            throw new PredictionServiceUnavailableException("Serviço de predição indisponível", ex);
        }
    }
}