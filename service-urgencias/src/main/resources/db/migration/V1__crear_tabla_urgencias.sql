CREATE TABLE IF NOT EXISTS atenciones_urgencia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    centro_id BIGINT NOT NULL,
    fecha VARCHAR(10) NOT NULL,
    hora VARCHAR(5) NOT NULL,
    prioridad VARCHAR(20) NOT NULL DEFAULT 'MEDIA',
    motivo_consulta TEXT NOT NULL,
    diagnostico TEXT,
    estado VARCHAR(20) DEFAULT 'EN_ESPERA',
    activo BOOLEAN DEFAULT TRUE
);
INSERT INTO atenciones_urgencia (paciente_id, centro_id, fecha, hora, prioridad, motivo_consulta, estado, activo) VALUES
  (1, 1, '2025-07-10', '22:15', 'ALTA', 'Dolor torácico agudo', 'EN_ESPERA', TRUE),
  (2, 2, '2025-07-11', '08:30', 'BAJA', 'Fiebre persistente', 'ATENDIDO', TRUE);
