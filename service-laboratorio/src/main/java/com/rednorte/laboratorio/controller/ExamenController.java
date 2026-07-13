package com.rednorte.laboratorio.controller;
import com.rednorte.laboratorio.dto.ExamenDTO;
import com.rednorte.laboratorio.service.ExamenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/laboratorio")
@Tag(name = "Laboratorio", description = "Gestión de exámenes de laboratorio")
public class ExamenController {
    @Autowired private ExamenService service;
    @Operation(summary = "Listar todos los exámenes") @GetMapping
    public ResponseEntity<List<ExamenDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    @Operation(summary = "Obtener examen por ID") @GetMapping("/{id}")
    public ResponseEntity<ExamenDTO> porId(@PathVariable Long id) {
        return service.porId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Exámenes por paciente") @GetMapping("/paciente/{id}")
    public ResponseEntity<List<ExamenDTO>> porPaciente(@PathVariable Long id) { return ResponseEntity.ok(service.porPaciente(id)); }
    @Operation(summary = "Solicitar examen") @PostMapping
    public ResponseEntity<ExamenDTO> solicitar(@Valid @RequestBody ExamenDTO dto) {
        return new ResponseEntity<>(service.solicitar(dto), HttpStatus.CREATED);
    }
    @Operation(summary = "Ingresar resultado") @PatchMapping("/{id}/resultado")
    public ResponseEntity<ExamenDTO> resultado(@PathVariable Long id, @RequestParam String resultado, @RequestParam String fecha) {
        return ResponseEntity.ok(service.ingresarResultado(id, resultado, fecha));
    }
    @Operation(summary = "Eliminar examen (baja lógica)") @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) { service.eliminarLogico(id); return ResponseEntity.noContent().build(); }
}
