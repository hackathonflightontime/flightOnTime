package com.flightontime.service;

import com.flightontime.dto.DelayStatsResponse;
import com.flightontime.dto.DelayStatsState;
import com.flightontime.model.Voo;
import com.flightontime.repository.VooRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class DelayStatsService {

    private final VooRepository vooRepository;

    public DelayStatsService(VooRepository vooRepository) {
        this.vooRepository = vooRepository;
    }

    public DelayStatsResponse calcularEstatisticasPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        List<Voo> voos = vooRepository.findByDepartureDateBetween(inicio, fim);

        List<Map<String, Object>> resultados = new ArrayList<>();
        List<Double> probabilidades = new ArrayList<>();

        for (Voo voo : voos) {
            Map<String, Object> payload = new LinkedHashMap<>();
            payload.put("companhia", voo.getCompany());
            payload.put("origem", voo.getOrigin());
            payload.put("destino", voo.getDestination());
            payload.put("estado_origem", voo.getStateOrigin());
            payload.put("estado_destino", voo.getStateDestitnation());
            payload.put("distancia_km", voo.getDistanceKM());
            payload.put("data_partida", voo.getDepartureDate());

            Map<String, Object> resultado = new LinkedHashMap<>();
            resultado.put("voo", payload);
            resultado.put("previsao", voo.getPrediction());
            resultado.put("probabilidade", voo.getProbability());

            resultados.add(resultado);

            // probability pode ser null se tiver dado antigo, então protege
            probabilidades.add(Optional.ofNullable(voo.getProbability()).orElse(0.0));
        }

        double media = probabilidades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double min = probabilidades.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
        double max = probabilidades.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);

        double variancia = probabilidades.stream()
                .mapToDouble(p -> Math.pow(p - media, 2))
                .sum() / (probabilidades.isEmpty() ? 1 : probabilidades.size());
        double desvio = Math.sqrt(variancia);

        return DelayStatsResponse.builder()
                .start(inicio)
                .end(fim)
                .totalVoos(voos.size())
                .resultados(resultados)
                .media(media)
                .min(min)
                .max(max)
                .desvio(desvio)
                .build();
    }

    public List<DelayStatsState> calcularPorEstado(LocalDateTime inicio, LocalDateTime fim) {
        List<Voo> voos = vooRepository.findByDepartureDateBetween(inicio, fim);

        Map<String, List<Double>> grupos = new HashMap<>();

        for (Voo voo : voos) {
            String estado = voo.getStateOrigin();
            double prob = Optional.ofNullable(voo.getProbability()).orElse(0.0);
            grupos.computeIfAbsent(estado, k -> new ArrayList<>()).add(prob);
        }

        List<DelayStatsState> estatisticas = new ArrayList<>();
        for (Map.Entry<String, List<Double>> entry : grupos.entrySet()) {
            List<Double> probs = entry.getValue();
            double media = probs.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double min = probs.stream().mapToDouble(Double::doubleValue).min().orElse(0);
            double max = probs.stream().mapToDouble(Double::doubleValue).max().orElse(0);

            double variancia = probs.stream()
                    .mapToDouble(p -> Math.pow(p - media, 2))
                    .sum() / (probs.isEmpty() ? 1 : probs.size());

            double desvio = Math.sqrt(variancia);

            estatisticas.add(new DelayStatsState(entry.getKey(), probs.size(), media, min, max, desvio));
        }

        // (opcional) ordenar por totalFlights desc para ficar mais bonito
        estatisticas.sort(Comparator.comparingLong(DelayStatsState::getTotalFlights).reversed());

        return estatisticas;
    }
}