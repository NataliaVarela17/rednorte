package com.rednorte.fichas.controller;
import com.rednorte.fichas.dto.FichaClinicaDTO;
import com.rednorte.fichas.service.FichaClinicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/fichas")
@Tag(name = "Fichas Clínicas", description = "Historial clínico de pacientes (consume Pacientes)")
public class FichaClinicaController {
    @Autowired private FichaClinicaService service;
    @Operation(summary = "Listar fichas") @GetMapping
    public ResponseEntity<List<FichaClinicaDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    @Operation(summary = "Obtener ficha por ID") @GetMapping("/{id}")
    public ResponseEntity<FichaClinicaDTO> porId(@PathVariable Long id) {
        return service.porId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Historial de un paciente") @GetMapping("/paciente/{id}")
    public ResponseEntity<List<FichaClinicaDTO>> porPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(service.porPaciente(id));
    }
    @Operation(summary = "Registrar ficha clínica") @PostMapping
    public ResponseEntity<FichaClinicaDTO> registrar(@Valid @RequestBody FichaClinicaDTO dto) {
        return new ResponseEntity<>(service.registrar(dto), HttpStatus.CREATED);
    }
    @Operation(summary = "Eliminar ficha (baja lógica)") @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarLogico(id); return ResponseEntity.noContent().build();
    }
}
