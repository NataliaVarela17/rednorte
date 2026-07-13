package com.rednorte.centros.controller;
import com.rednorte.centros.dto.CentroDTO;
import com.rednorte.centros.service.CentroMedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/centros")
@Tag(name = "Centros de Salud", description = "Gestión de hospitales y centros de salud RedNorte")
public class CentroMedicoController {
    @Autowired private CentroMedicoService service;

    @Operation(summary = "Listar todos los centros activos")
    @ApiResponse(responseCode = "200", description = "Lista de centros activos")
    @GetMapping
    public ResponseEntity<List<CentroDTO>> listar() {
        return ResponseEntity.ok(service.obtenerTodosActivos());
    }

    @Operation(summary = "Obtener centro por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Centro encontrado"),
        @ApiResponse(responseCode = "404", description = "Centro no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CentroDTO> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar nuevo centro de salud")
    @ApiResponse(responseCode = "201", description = "Centro creado exitosamente")
    @PostMapping
    public ResponseEntity<CentroDTO> crear(@Valid @RequestBody CentroDTO dto) {
        return new ResponseEntity<>(service.registrar(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar datos de un centro")
    @PutMapping("/{id}")
    public ResponseEntity<CentroDTO> actualizar(@PathVariable Long id, @Valid @RequestBody CentroDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @Operation(summary = "Desactivar un centro de salud (baja lógica)")
    @ApiResponse(responseCode = "204", description = "Centro desactivado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        service.desactivar(id);
        return ResponseEntity.noContent().build();
    }
}
