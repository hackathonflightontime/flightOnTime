package com.flightontime.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.flightontime.dto.PredictResponse;
import com.flightontime.service.PredictService;

@WebMvcTest(PredictController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PredictControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PredictService predictService;

    @Test
    void shouldReturnPredictionSuccessfully() throws Exception {

        Mockito.when(predictService.predict(Mockito.any()))
                .thenReturn(new PredictResponse(true, 0.85));

        String jsonRequest = """
            {
              "companhia": "AZUL",
              "origem": "GRU",
              "destino": "CGH",
              "dataPartida": "2025-01-10",
              "distancia": 350
            }
            """;

        mockMvc.perform(post("/predict")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.delay").value(true))
                .andExpect(jsonPath("$.probability").value(0.85));
    }
}
