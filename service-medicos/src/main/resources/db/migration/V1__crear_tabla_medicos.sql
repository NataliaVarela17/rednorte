CREATE TABLE IF NOT EXISTS medicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(12) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100) NOT NULL,
    centro_id BIGINT NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(150),
    activo BOOLEAN DEFAULT TRUE
);
INSERT INTO medicos (rut, nombre, apellido, especialidad, centro_id, telefono, email, activo) VALUES
  ('11222333-4', 'Ana', 'Martínez', 'Cardiología', 1, '987100100', 'ana@rednorte.cl', TRUE),
  ('22333444-5', 'Pedro', 'Soto', 'Medicina General', 1, '987200200', 'pedro@rednorte.cl', TRUE),
  ('33444555-6', 'Laura', 'Díaz', 'Pediatría', 2, '987300300', 'laura@rednorte.cl', TRUE);
