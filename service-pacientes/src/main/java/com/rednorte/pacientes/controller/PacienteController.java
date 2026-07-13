package com.rednorte.pacientes.controller;
import com.rednorte.pacientes.dto.PacienteDTO;
import com.rednorte.pacientes.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/pacientes")
@Tag(name = "Pacientes", description = "Gestión de pacientes de RedNorte")
public class PacienteController {
    @Autowired private PacienteService service;

    @Operation(summary = "Listar pacientes activos")
    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listar() { return ResponseEntity.ok(service.listarActivos()); }

    @Operation(summary = "Buscar paciente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> porId(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar paciente por RUT")
    @GetMapping("/rut/{rut}")
    public ResponseEntity<PacienteDTO> porRut(@PathVariable String rut) {
        return service.buscarPorRut(rut).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar nuevo paciente")
    @PostMapping
    public ResponseEntity<PacienteDTO> crear(@Valid @RequestBody PacienteDTO dto) {
        return new ResponseEntity<>(service.registrar(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar paciente")
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PacienteDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @Operation(summary = "Eliminar paciente (baja lógica)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarLogico(id); return ResponseEntity.noContent().build();
    }
}
