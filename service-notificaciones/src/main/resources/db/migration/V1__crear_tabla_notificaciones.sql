CREATE TABLE IF NOT EXISTS notificaciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    destinatario_id BIGINT NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    titulo VARCHAR(200) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha VARCHAR(10) NOT NULL,
    leida BOOLEAN DEFAULT FALSE,
    activo BOOLEAN DEFAULT TRUE
);
INSERT INTO notificaciones (destinatario_id, tipo, titulo, mensaje, fecha, leida, activo) VALUES
  (1, 'CITA', 'Recordatorio de cita', 'Tiene una cita mañana a las 09:00 con Dr. Ana Martínez', '2025-07-14', FALSE, TRUE),
  (2, 'EXAMEN', 'Resultado de examen disponible', 'Su perfil lipídico ya está disponible para consulta', '2025-07-10', FALSE, TRUE);
