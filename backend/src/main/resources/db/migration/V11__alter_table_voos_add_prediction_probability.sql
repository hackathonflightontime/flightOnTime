-- Adiciona campos para armazenar resultado da predição no banco
ALTER TABLE voos
ADD COLUMN probability DOUBLE PRECISION NOT NULL DEFAULT 0.0;

ALTER TABLE voos
ADD COLUMN prediction VARCHAR(20) NOT NULL DEFAULT 'Pontual';

-- Índice opcional para consultas por data de criação
CREATE INDEX IF NOT EXISTS idx_voos_created_at ON voos (created_at);