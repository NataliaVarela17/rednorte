CREATE TABLE IF NOT EXISTS examenes_laboratorio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    tipo_examen VARCHAR(100) NOT NULL,
    fecha_solicitud VARCHAR(10) NOT NULL,
    fecha_resultado VARCHAR(10),
    resultado TEXT,
    estado VARCHAR(20) DEFAULT 'SOLICITADO',
    activo BOOLEAN DEFAULT TRUE
);
INSERT INTO examenes_laboratorio (paciente_id, medico_id, tipo_examen, fecha_solicitud, estado, activo) VALUES
  (1, 1, 'Hemograma completo', '2025-07-01', 'SOLICITADO', TRUE),
  (2, 2, 'Perfil lipídico', '2025-07-02', 'COMPLETADO', TRUE);
