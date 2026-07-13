CREATE TABLE IF NOT EXISTS medicamentos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    principio_activo VARCHAR(100) NOT NULL,
    presentacion VARCHAR(50) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    stock_minimo INT DEFAULT 10,
    centro_id BIGINT NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);
INSERT INTO medicamentos (nombre, principio_activo, presentacion, stock, stock_minimo, centro_id, activo) VALUES
  ('Losartán 50mg', 'Losartán potásico', 'Comprimido', 150, 20, 1, TRUE),
  ('Amoxicilina 500mg', 'Amoxicilina', 'Cápsula', 8, 15, 1, TRUE),
  ('Paracetamol 500mg', 'Paracetamol', 'Comprimido', 300, 50, 2, TRUE);
