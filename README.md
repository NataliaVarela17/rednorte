# 🏥 RedNorte - Sistema de Microservicios de Salud

Sistema distribuido de gestión hospitalaria para la Red de Salud del Norte, implementado con **Spring Boot 3.2** y arquitectura de **microservicios**.

---

## 📐 Arquitectura

```
                    ┌─────────────────┐
                    │   API Gateway   │
                    │   Puerto 8080   │
                    └────────┬────────┘
                             │
        ┌────────────────────┼───────────────────┐
        │                    │                    │
   ┌────▼────┐          ┌────▼────┐         ┌────▼────┐
   │Centros  │          │Pacientes│         │ Médicos │
   │  8081   │          │  8083   │         │  8084   │
   └─────────┘          └────┬────┘         └────┬────┘
   ┌─────────┐               │                   │
   │Recursos │          ┌────▼───────────────────▼────┐
   │  8082   │          │           Citas              │
   └─────────┘          │           8085              │
   ┌─────────┐          └──────────────────────────────┘
   │Fichas   │◄── consume Pacientes
   │  8086   │
   └─────────┘
   ┌─────────┐   ┌──────────┐   ┌────────────┐   ┌──────────────┐
   │Farmacia │   │Laboratorio│  │ Urgencias  │   │Notificaciones│
   │  8087   │   │   8088   │   │   8089     │   │    8090      │
   └─────────┘   └──────────┘   └────────────┘   └──────────────┘
```

## 🧩 Microservicios

| Servicio            | Puerto | Base de Datos          | Descripción                        |
|---------------------|--------|------------------------|------------------------------------|
| api-gateway         | 8080   | -                      | Enrutador centralizado             |
| service-centros     | 8081   | rednorte_centros       | Hospitales y centros de salud      |
| service-recursos    | 8082   | rednorte_recursos      | Salas y equipamiento médico        |
| service-pacientes   | 8083   | rednorte_pacientes     | Registro de pacientes              |
| service-medicos     | 8084   | rednorte_medicos       | Cuerpo médico                      |
| service-citas       | 8085   | rednorte_citas         | Agendamiento (consume 3 servicios) |
| service-fichas      | 8086   | rednorte_fichas        | Historial clínico                  |
| service-farmacia    | 8087   | rednorte_farmacia      | Medicamentos e inventario          |
| service-laboratorio | 8088   | rednorte_laboratorio   | Exámenes de laboratorio            |
| service-urgencias   | 8089   | rednorte_urgencias     | Urgencias (consume Centros)        |
| service-notificaciones | 8090 | rednorte_notificaciones | Sistema de notificaciones        |

---

## 🚀 Ejecución con Docker

### Prerrequisitos
- Docker Desktop instalado y en ejecución
- Java 17 + Maven (para compilar antes de dockerizar)

### Paso 1 — Compilar todos los microservicios
```bash
# Desde la raíz del proyecto
mvn clean package -DskipTests
```

### Paso 2 — Levantar con Docker Compose
```bash
docker-compose up --build
```

### Paso 3 — Verificar servicios
```bash
# Verificar todos los contenedores
docker-compose ps

# Ver logs de un servicio específico
docker-compose logs -f service-citas
```

---

## 🌐 Endpoints principales (vía Gateway: localhost:8080)

### Centros de Salud
| Método | URL                        | Descripción               |
|--------|----------------------------|---------------------------|
| GET    | /api/centros               | Listar centros activos    |
| GET    | /api/centros/{id}          | Obtener por ID            |
| POST   | /api/centros               | Crear nuevo centro        |
| PUT    | /api/centros/{id}          | Actualizar centro         |
| DELETE | /api/centros/{id}          | Desactivar (baja lógica)  |

### Pacientes
| Método | URL                        | Descripción               |
|--------|----------------------------|---------------------------|
| GET    | /api/pacientes             | Listar pacientes activos  |
| GET    | /api/pacientes/{id}        | Buscar por ID             |
| GET    | /api/pacientes/rut/{rut}   | Buscar por RUT            |
| POST   | /api/pacientes             | Registrar paciente        |
| PUT    | /api/pacientes/{id}        | Actualizar paciente       |
| DELETE | /api/pacientes/{id}        | Baja lógica               |

### Citas (con enriquecimiento via WebClient)
| Método | URL                           | Descripción                         |
|--------|-------------------------------|-------------------------------------|
| GET    | /api/citas                    | Listar citas                        |
| GET    | /api/citas/{id}               | Ver detalle enriquecido             |
| GET    | /api/citas/paciente/{id}      | Citas de un paciente                |
| POST   | /api/citas                    | Agendar cita                        |
| PATCH  | /api/citas/{id}/estado        | Cambiar estado                      |
| DELETE | /api/citas/{id}               | Cancelar                            |

---

## 📖 Documentación Swagger

Cada microservicio expone su Swagger UI en `/swagger-ui.html`:

- Centros: http://localhost:8081/swagger-ui.html
- Pacientes: http://localhost:8083/swagger-ui.html
- Médicos: http://localhost:8084/swagger-ui.html
- Citas: http://localhost:8085/swagger-ui.html
- Fichas: http://localhost:8086/swagger-ui.html
- Farmacia: http://localhost:8087/swagger-ui.html
- Laboratorio: http://localhost:8088/swagger-ui.html
- Urgencias: http://localhost:8089/swagger-ui.html
- Notificaciones: http://localhost:8090/swagger-ui.html

---

## 🧪 Pruebas Unitarias

```bash
# Ejecutar todos los tests
mvn test

# Ejecutar tests de un servicio específico
cd service-citas && mvn test
```

---

## 🔗 Comunicación entre Microservicios (WebClient)

Los siguientes servicios consumen endpoints de otros microservicios:

- **service-citas** → consume `/api/pacientes/{id}`, `/api/medicos/{id}`, `/api/centros/{id}`
- **service-fichas** → consume `/api/pacientes/{id}` (verifica existencia)
- **service-urgencias** → consume `/api/centros/{id}` (verifica existencia)

---

## 🗂️ Tecnologías utilizadas

- **Spring Boot 3.2.5**
- **Spring Cloud Gateway 2023.0.1**
- **Spring Data JPA + Hibernate**
- **Flyway** (migraciones de base de datos)
- **MySQL 8.0**
- **WebClient** (comunicación reactiva entre servicios)
- **Swagger / OpenAPI 3** (springdoc 2.5.0)
- **Lombok**
- **Bean Validation (JSR 380)**
- **JUnit 5 + Mockito** (pruebas unitarias)
- **Docker + Docker Compose**
