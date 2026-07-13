package com.rednorte.recursos.controller;
import com.rednorte.recursos.dto.*;
import com.rednorte.recursos.service.RecursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Tag(name = "Recursos", description = "Gestión de salas y equipamiento médico de RedNorte")
public class RecursoController {
    @Autowired private RecursoService service;

    // ─── SALAS ──────────────────────────────────────────────────
    @Operation(summary = "Listar salas disponibles")
    @GetMapping("/api/salas")
    public ResponseEntity<List<SalaDTO>> listarSalas() {
        return ResponseEntity.ok(service.obtenerSalasDisponibles());
    }

    @Operation(summary = "Listar todas las salas activas")
    @GetMapping("/api/salas/todas")
    public ResponseEntity<List<SalaDTO>> todasSalas() {
        return ResponseEntity.ok(service.obtenerTodasSalas());
    }

    @Operation(summary = "Salas por centro")
    @GetMapping("/api/salas/centro/{centroId}")
    public ResponseEntity<List<SalaDTO>> porCentro(@PathVariable Long centroId) {
        return ResponseEntity.ok(service.obtenerSalasPorCentro(centroId));
    }

    @Operation(summary = "Obtener sala por ID")
    @GetMapping("/api/salas/{id}")
    public ResponseEntity<SalaDTO> obtenerSala(@PathVariable Long id) {
        return service.obtenerSalaPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar nueva sala")
    @ApiResponse(responseCode = "201", description = "Sala creada")
    @PostMapping("/api/salas")
    public ResponseEntity<SalaDTO> crearSala(@Valid @RequestBody SalaDTO dto) {
        return new ResponseEntity<>(service.crearSala(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar sala")
    @PutMapping("/api/salas/{id}")
    public ResponseEntity<SalaDTO> actualizarSala(@PathVariable Long id, @Valid @RequestBody SalaDTO dto) {
        return ResponseEntity.ok(service.actualizarSala(id, dto));
    }

    @Operation(summary = "Desactivar sala (baja lógica)")
    @DeleteMapping("/api/salas/{id}")
    public ResponseEntity<Void> desactivarSala(@PathVariable Long id) {
        service.desactivarSala(id); return ResponseEntity.noContent().build();
    }

    // ─── EQUIPAMIENTO ───────────────────────────────────────────
    @Operation(summary = "Listar todo el equipamiento activo")
    @GetMapping("/api/equipamiento")
    public ResponseEntity<List<EquipamientoDTO>> listarEquipamiento() {
        return ResponseEntity.ok(service.obtenerEquipamiento());
    }

    @Operation(summary = "Equipamiento de una sala")
    @GetMapping("/api/equipamiento/sala/{salaId}")
    public ResponseEntity<List<EquipamientoDTO>> porSala(@PathVariable Long salaId) {
        return ResponseEntity.ok(service.obtenerEquipamientoPorSala(salaId));
    }

    @Operation(summary = "Obtener equipamiento por ID")
    @GetMapping("/api/equipamiento/{id}")
    public ResponseEntity<EquipamientoDTO> obtenerEquipo(@PathVariable Long id) {
        return service.obtenerEquipoPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar equipamiento")
    @PostMapping("/api/equipamiento")
    public ResponseEntity<EquipamientoDTO> crearEquipamiento(@Valid @RequestBody EquipamientoDTO dto) {
        return new ResponseEntity<>(service.crearEquipamiento(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar estado de equipamiento (OPERATIVO / MANTENIMIENTO / FUERA_DE_SERVICIO)")
    @PatchMapping("/api/equipamiento/{id}/estado")
    public ResponseEntity<EquipamientoDTO> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(service.actualizarEstadoEquipo(id, estado));
    }

    @Operation(summary = "Desactivar equipamiento (baja lógica)")
    @DeleteMapping("/api/equipamiento/{id}")
    public ResponseEntity<Void> desactivarEquipo(@PathVariable Long id) {
        service.desactivarEquipo(id); return ResponseEntity.noContent().build();
    }
}
