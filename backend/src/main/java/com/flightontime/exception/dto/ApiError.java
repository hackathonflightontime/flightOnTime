package com.flightontime.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Estrutura padrão de erro retornada pela API", example = """
                {
                  "timestamp": "2026-01-16T08:42:13.454333",
                  "status": 400,
                  "error": "Bad Request",
                  "message": "Erro de validação",
                  "path": "/predict",
                  "fieldErrors": {
                    "companhia": "Companhia é obrigatória",
                    "origem": "Aeroporto de origem é obrigatório"
                  }
                }
                """)
public record ApiError(

                @Schema(description = "Data e hora em que o erro ocorreu", example = "2026-01-16T08:42:13.454333") LocalDateTime timestamp,

                @Schema(description = "Código HTTP do erro", example = "400") int status,

                @Schema(description = "Descrição curta do erro HTTP", example = "Bad Request") String error,

                @Schema(description = "Mensagem detalhada do erro", example = "Erro de validação") String message,

                @Schema(description = "Endpoint onde o erro ocorreu", example = "/predict") String path,

                @Schema(description = "Erros de validação por campo (quando aplicável)", example = "{\"companhia\":\"Companhia é obrigatória\"}") Map<String, String> fieldErrors) {
}