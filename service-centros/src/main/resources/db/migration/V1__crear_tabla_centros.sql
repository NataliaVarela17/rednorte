CREATE TABLE IF NOT EXISTS centros_salud (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    direccion VARCHAR(150) NOT NULL,
    telefono VARCHAR(20),
    comuna VARCHAR(100),
    tipo VARCHAR(50) NOT NULL DEFAULT 'HOSPITAL',
    activo BOOLEAN DEFAULT TRUE
);

INSERT INTO centros_salud (nombre, direccion, telefono, comuna, tipo, activo) VALUES
  ('Hospital Regional Norte', 'Av. Principal 1234', '222333444', 'Antofagasta', 'HOSPITAL', TRUE),
  ('CESFAM Norte', 'Calle Salud 456', '222333555', 'Calama', 'CESFAM', TRUE),
  ('Clínica del Cobre', 'Av. Minería 789', '222333666', 'Copiapó', 'CLINICA', TRUE);
