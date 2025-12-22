package com.flightontime.service;

import com.flightontime.dto.VooRequest;
import com.flightontime.dto.VooResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Service
public class VooService {

    private final WebClient.Builder webClient;

    public VooService(WebClient.Builder webClient) {
        this.webClient = webClient;
    }


    /*public VooService(VooRepository vooRepository) {
        this.vooRepository = vooRepository;
    }*/
    //Envia para Modelo
    public VooResponse prever(VooRequest request) {

        Double probabilidade = webClient.build()
                .post()
                .uri("http://localhost:8000/predict-model")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Double.class)
                .block();

        String previsao = probabilidade >= 0.5 ? "Atrasado" : "Pontual";

        return new VooResponse(previsao, probabilidade);
    }

    /*public VooResponse prever(VooRequest request) {
        // 1. Converter DTO → Entity
        Voo voo = new Voo(
                request.getCompanhia(),
                request.getOrigem(),
                request.getDestino(),
                LocalDateTime.parse(request.getData_partida()),
                request.getDistancia_km()
        );

        // 2. Persistir no banco (opcional, útil para histórico)
        vooRepository.save(voo);

        // 3. Lógica de previsão (mock por enquanto)
        double probabilidade = calcularProbabilidade(voo);
        String previsao = probabilidade >= 0.5 ? "Atrasado" : "Pontual";

        // 4. Retornar DTO de saída
        return new VooResponse(previsao, probabilidade);
    }

    private double calcularProbabilidade(Voo voo) {
        // Regra simples só para teste:
        boolean tarde = voo.getDataPartida().getHour() >= 14 && voo.getDataPartida().getHour() <= 20;
        double base = tarde ? 0.7 : 0.3;
        double ajusteDistancia = voo.getDistanciaKm() > 800 ? 0.1 : 0.0;
        return Math.min(1.0, Math.max(0.0, base + ajusteDistancia));
    }*/
}