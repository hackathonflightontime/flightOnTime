package com.flightontime.unit.dto;

import com.flightontime.dto.VooResponse;
import org.junit.jupiter.api.Test;

public class VooResponseTest {
    
    @Test
    public void teste1() {
        VooResponse r = new VooResponse("Atrasado", 0.78);
        if (!"Atrasado".equals(r.getPrediction())) {
            throw new RuntimeException("Falhou: prediction não é 'Atrasado'");
        }
    }
    
    @Test
    public void teste2() {
        VooResponse r = new VooResponse("No Horário", 0.92);
        if (r.getProbability() != 0.92) {
            throw new RuntimeException("Falhou: probability não é 0.92");
        }
    }
    
    @Test
    public void teste3() {
        VooResponse r = new VooResponse("Teste", 1.0);
        if (r.getProbability() != 1.0) {
            throw new RuntimeException("Falhou: probability não é 1.0");
        }
    }
}