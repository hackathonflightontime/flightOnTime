CREATE TABLE flights_historic (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    companhia VARCHAR(2) NOT NULL,        -- op_unique_carrier (ex.: AA, DL, UA)
    origem VARCHAR(3) NOT NULL,           -- IATA origin (ex.: JFK, LAX)
    estado_origem VARCHAR(50) NOT NULL,   -- origin_state_nm
    destino VARCHAR(3) NOT NULL,          -- IATA dest (ex.: GRU, SFO)
    estado_destino VARCHAR(50) NOT NULL,  -- dest_state_nm
    data_partida TIMESTAMP NOT NULL,      -- fl_date + crs_dep_time
    distancia_km INT NOT NULL             -- distance
);