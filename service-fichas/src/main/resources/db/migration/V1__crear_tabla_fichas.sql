CREATE TABLE IF NOT EXISTS fichas_clinicas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    cita_id BIGINT,
    fecha VARCHAR(10) NOT NULL,
    diagnostico TEXT NOT NULL,
    tratamiento TEXT,
    observaciones TEXT,
    activo BOOLEAN DEFAULT TRUE
);
INSERT INTO fichas_clinicas (paciente_id, medico_id, fecha, diagnostico, tratamiento, activo) VALUES
  (1, 1, '2025-06-01', 'Hipertensión arterial leve', 'Losartán 50mg diario', TRUE),
  (2, 3, '2025-06-05', 'Faringitis aguda', 'Amoxicilina 500mg cada 8h por 7 días', TRUE);
