package com.rednorte.medicos.controller;
import com.rednorte.medicos.dto.MedicoDTO;
import com.rednorte.medicos.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/medicos")
@Tag(name = "Médicos", description = "Gestión del cuerpo médico de RedNorte")
public class MedicoController {
    @Autowired private MedicoService service;

    @Operation(summary = "Listar médicos activos") @GetMapping
    public ResponseEntity<List<MedicoDTO>> listar() { return ResponseEntity.ok(service.listarActivos()); }

    @Operation(summary = "Buscar médico por ID") @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> porId(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Filtrar por especialidad") @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<MedicoDTO>> porEspecialidad(@PathVariable String especialidad) {
        return ResponseEntity.ok(service.porEspecialidad(especialidad));
    }

    @Operation(summary = "Filtrar por centro") @GetMapping("/centro/{centroId}")
    public ResponseEntity<List<MedicoDTO>> porCentro(@PathVariable Long centroId) {
        return ResponseEntity.ok(service.porCentro(centroId));
    }

    @Operation(summary = "Registrar médico") @PostMapping
    public ResponseEntity<MedicoDTO> crear(@Valid @RequestBody MedicoDTO dto) {
        return new ResponseEntity<>(service.registrar(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar médico") @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody MedicoDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @Operation(summary = "Desactivar médico (baja lógica)") @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        service.desactivar(id); return ResponseEntity.noContent().build();
    }
}
