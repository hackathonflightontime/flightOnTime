CREATE TABLE voos (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    company VARCHAR(2) NOT NULL,
    origin VARCHAR(3) NOT NULL,
    destination VARCHAR(3) NOT NULL,

    departure_date TIMESTAMP NOT NULL,
    distance_km INTEGER NOT NULL,

    state_destination VARCHAR(255) NOT NULL,
    state_origin VARCHAR(255) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

--opcionais índices para melhorar performance em consultas comuns
CREATE INDEX idx_voos_company ON voos (company);
CREATE INDEX idx_voos_origin_destination ON voos (origin, destination);
CREATE INDEX idx_voos_departure_date ON voos (departure_date);