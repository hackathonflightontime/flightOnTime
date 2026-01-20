CREATE TABLE predictions (
    id BIGSERIAL PRIMARY KEY,

    companhia VARCHAR(10) NOT NULL,
    origem VARCHAR(10) NOT NULL,
    destino VARCHAR(10) NOT NULL,
    estado_origem VARCHAR(5) NOT NULL,
    estado_destino VARCHAR(5) NOT NULL,
    distancia INTEGER NOT NULL,
    data_partida TIMESTAMP NOT NULL,

    previsao BOOLEAN NOT NULL,
    probabilidade NUMERIC(5,4) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
