CREATE TABLE IF NOT EXISTS citas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    centro_id BIGINT NOT NULL,
    fecha VARCHAR(10) NOT NULL,
    hora VARCHAR(5) NOT NULL,
    estado VARCHAR(20) DEFAULT 'PENDIENTE',
    observaciones VARCHAR(200),
    activo BOOLEAN DEFAULT TRUE
);
INSERT INTO citas (paciente_id, medico_id, centro_id, fecha, hora, estado, activo) VALUES
  (1, 1, 1, '2025-07-15', '09:00', 'PENDIENTE', TRUE),
  (2, 2, 1, '2025-07-16', '10:30', 'PENDIENTE', TRUE);
