package com.flightontime.unit.controller;

import com.flightontime.controller.VooController;
import com.flightontime.dto.VooRequest;
import com.flightontime.dto.VooResponse;
import com.flightontime.service.VooService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = VooController.class)
@AutoConfigureMockMvc(addFilters = false)
class PredictControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VooService vooService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRetornar200QuandoRequestValido() throws Exception {
        VooRequest request = new VooRequest();
        request.setCompany("AZ");
        request.setOrigin("GIG");
        request.setDestination("GRU");
        request.setStateOrigin("RJ");
        request.setStateDestination("SP");
        request.setDistanceKm(420);
        request.setDepartureDate(LocalDateTime.now().plusDays(1));

        Mockito.when(vooService.predict(Mockito.any()))
                .thenReturn(new VooResponse("Atrasado", 0.85));

        mockMvc.perform(
                post("/predict")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.previsao").value("Atrasado"))
                .andExpect(jsonPath("$.probabilidade").value(0.85));
    }

    @Test
    void deveRetornar400QuandoRequestInvalido() throws Exception {
        mockMvc.perform(
                post("/predict")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
        )
                .andExpect(status().isBadRequest());
    }
}
