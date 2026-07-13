CREATE TABLE IF NOT EXISTS pacientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(12) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(150),
    direccion VARCHAR(200),
    fecha_nacimiento VARCHAR(10),
    activo BOOLEAN DEFAULT TRUE
);
INSERT INTO pacientes (rut, nombre, apellido, telefono, email, direccion, fecha_nacimiento, activo) VALUES
  ('12345678-9', 'Carlos', 'González', '987001001', 'carlos@mail.com', 'Av. Norte 10', '1985-03-12', TRUE),
  ('98765432-1', 'María', 'López', '987002002', 'maria@mail.com', 'Calle Sur 20', '1990-07-25', TRUE);
