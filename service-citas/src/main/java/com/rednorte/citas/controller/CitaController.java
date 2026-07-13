package com.rednorte.citas.controller;
import com.rednorte.citas.dto.CitaDTO;
import com.rednorte.citas.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/citas")
@Tag(name = "Citas", description = "Agendamiento de citas médicas (consume Pacientes, Médicos y Centros)")
public class CitaController {
    @Autowired private CitaService service;

    @Operation(summary = "Listar citas activas") @GetMapping
    public ResponseEntity<List<CitaDTO>> listar() { return ResponseEntity.ok(service.listarActivas()); }

    @Operation(summary = "Ver detalle enriquecido de una cita") @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> porId(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Citas de un paciente") @GetMapping("/paciente/{id}")
    public ResponseEntity<List<CitaDTO>> porPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(service.porPaciente(id));
    }

    @Operation(summary = "Citas de un médico") @GetMapping("/medico/{id}")
    public ResponseEntity<List<CitaDTO>> porMedico(@PathVariable Long id) {
        return ResponseEntity.ok(service.porMedico(id));
    }

    @Operation(summary = "Agendar nueva cita") @PostMapping
    public ResponseEntity<CitaDTO> agendar(@Valid @RequestBody CitaDTO dto) {
        return new ResponseEntity<>(service.agendar(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Cambiar estado de una cita") @PatchMapping("/{id}/estado")
    public ResponseEntity<CitaDTO> estado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(service.cambiarEstado(id, estado));
    }

    @Operation(summary = "Cancelar cita") @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        service.cancelar(id); return ResponseEntity.noContent().build();
    }
}
