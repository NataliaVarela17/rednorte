CREATE TABLE IF NOT EXISTS salas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    tipo VARCHAR(50) NOT NULL,
    centro_id BIGINT NOT NULL,
    disponible BOOLEAN DEFAULT TRUE,
    activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS equipamiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    sala_id BIGINT,
    estado VARCHAR(20) DEFAULT 'OPERATIVO',
    activo BOOLEAN DEFAULT TRUE
);

INSERT INTO salas (codigo, tipo, centro_id, disponible, activo) VALUES
  ('SALA-01', 'CONSULTA', 1, TRUE, TRUE),
  ('SALA-02', 'CIRUGIA', 1, FALSE, TRUE),
  ('SALA-03', 'PEDIATRIA', 2, TRUE, TRUE),
  ('SALA-04', 'UCI', 1, TRUE, TRUE);

INSERT INTO equipamiento (nombre, sala_id, estado, activo) VALUES
  ('Electrocardiograma', 1, 'OPERATIVO', TRUE),
  ('Bisturí eléctrico', 2, 'OPERATIVO', TRUE),
  ('Monitor de signos vitales', 4, 'OPERATIVO', TRUE),
  ('Desfibrilador', 4, 'MANTENIMIENTO', TRUE);
