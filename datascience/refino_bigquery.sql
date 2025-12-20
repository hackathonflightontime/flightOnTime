/**
 * DATA PIPELINE: Refinamento e Engenharia de Features
 * Projeto: FlightOnTime - Predict Flight Delays
 * Objetivo: Criar a tabela final para treinamento do modelo (Random Forest)
 */

CREATE OR REPLACE TABLE `flightontime-hackathon.dados_voos.tabela_treinamento_final` AS
SELECT 
    OP_UNIQUE_CARRIER AS companhia,
    ORIGIN AS origem,
    ORIGIN_STATE_NM AS estado_origem, 
    DEST AS destino,
    DEST_STATE_NM AS estado_destino, 
    DISTANCE AS distancia,
    
    -- Feature Engineering: Definição do Target (Binary Classification)
    -- 1: Atrasado (delay > 15 min) | 0: Pontual/Atraso irrelevante
    CASE 
        WHEN DEP_DELAY > 15 THEN 1 
        ELSE 0 
    END AS atrasado,
    
    FL_DATE AS data_voo,
    CAST(CRS_DEP_TIME AS INT64) AS hora_partida_prevista
FROM 
    `flightontime-hackathon.dados_voos.tabela_bruta_drive`
WHERE 
    CANCELLED = 0 -- Data Cleaning: Remoção de voos cancelados
