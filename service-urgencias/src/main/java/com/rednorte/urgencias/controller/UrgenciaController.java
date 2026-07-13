package com.rednorte.urgencias.controller;
import com.rednorte.urgencias.dto.UrgenciaDTO;
import com.rednorte.urgencias.service.UrgenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/urgencias")
@Tag(name = "Urgencias", description = "Gestión de urgencias médicas (consume Centros)")
public class UrgenciaController {
    @Autowired private UrgenciaService service;
    @Operation(summary = "Listar atenciones de urgencia") @GetMapping
    public ResponseEntity<List<UrgenciaDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    @Operation(summary = "Obtener urgencia por ID") @GetMapping("/{id}")
    public ResponseEntity<UrgenciaDTO> porId(@PathVariable Long id) {
        return service.porId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Pacientes en espera") @GetMapping("/en-espera")
    public ResponseEntity<List<UrgenciaDTO>> enEspera() { return ResponseEntity.ok(service.enEspera()); }
    @Operation(summary = "Registrar ingreso a urgencias") @PostMapping
    public ResponseEntity<UrgenciaDTO> registrar(@Valid @RequestBody UrgenciaDTO dto) {
        return new ResponseEntity<>(service.registrar(dto), HttpStatus.CREATED);
    }
    @Operation(summary = "Registrar atención y diagnóstico") @PatchMapping("/{id}/atender")
    public ResponseEntity<UrgenciaDTO> atender(@PathVariable Long id, @RequestParam String diagnostico) {
        return ResponseEntity.ok(service.atender(id, diagnostico));
    }
    @Operation(summary = "Cerrar caso de urgencia (baja lógica)") @DeleteMapping("/{id}")
    public ResponseEntity<Void> cerrar(@PathVariable Long id) {
        service.cerrar(id); return ResponseEntity.noContent().build();
    }
}
