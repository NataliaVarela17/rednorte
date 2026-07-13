-- Script de inicialización de bases de datos RedNorte
-- Se ejecuta automáticamente en el contenedor MySQL

CREATE DATABASE IF NOT EXISTS rednorte_centros CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS rednorte_recursos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS rednorte_pacientes CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS rednorte_medicos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS rednorte_citas CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS rednorte_fichas CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS rednorte_farmacia CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS rednorte_laboratorio CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS rednorte_urgencias CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS rednorte_notificaciones CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

GRANT ALL PRIVILEGES ON rednorte_centros.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON rednorte_recursos.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON rednorte_pacientes.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON rednorte_medicos.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON rednorte_citas.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON rednorte_fichas.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON rednorte_farmacia.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON rednorte_laboratorio.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON rednorte_urgencias.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON rednorte_notificaciones.* TO 'root'@'%';

FLUSH PRIVILEGES;
