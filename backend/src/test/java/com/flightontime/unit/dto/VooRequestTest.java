package com.flightontime.unit.dto;

import com.flightontime.dto.VooRequest;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class VooRequestTest {
    
    @Test
    public void testPrimeiroTeste() {
        System.out.println("=== INICIANDO PRIMEIRO TESTE ===");
        
        // 1. Crie o objeto
        VooRequest request = new VooRequest();
        
        // 2. Configure os dados
        request.setCompany("AZ");
        request.setOrigin("GIG");
        request.setDestination("GRU");
        
        // 3. Verifique
        if (!"AZ".equals(request.getCompany())) {
            throw new AssertionError("Companhia deveria ser AZ");
        }
        
        if (!"GIG".equals(request.getOrigin())) {
            throw new AssertionError("Origem deveria ser GIG");
        }
        
        System.out.println("✅ TESTE PASSOU! VooRequest funciona!");
        System.out.println("Companhia: " + request.getCompany());
        System.out.println("Origem: " + request.getOrigin());
        System.out.println("Destino: " + request.getDestination());
    }
}