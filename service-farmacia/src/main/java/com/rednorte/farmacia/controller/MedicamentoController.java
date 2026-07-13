package com.rednorte.farmacia.controller;
import com.rednorte.farmacia.dto.MedicamentoDTO;
import com.rednorte.farmacia.service.MedicamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/farmacia")
@Tag(name = "Farmacia", description = "Gestión de medicamentos e inventario farmacéutico")
public class MedicamentoController {
    @Autowired private MedicamentoService service;
    @Operation(summary = "Listar medicamentos") @GetMapping
    public ResponseEntity<List<MedicamentoDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    @Operation(summary = "Buscar medicamento por ID") @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDTO> porId(@PathVariable Long id) {
        return service.porId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Medicamentos con stock bajo") @GetMapping("/stock-bajo")
    public ResponseEntity<List<MedicamentoDTO>> stockBajo() { return ResponseEntity.ok(service.stockBajo()); }
    @Operation(summary = "Medicamentos por centro") @GetMapping("/centro/{centroId}")
    public ResponseEntity<List<MedicamentoDTO>> porCentro(@PathVariable Long centroId) {
        return ResponseEntity.ok(service.porCentro(centroId));
    }
    @Operation(summary = "Registrar medicamento") @PostMapping
    public ResponseEntity<MedicamentoDTO> registrar(@Valid @RequestBody MedicamentoDTO dto) {
        return new ResponseEntity<>(service.registrar(dto), HttpStatus.CREATED);
    }
    @Operation(summary = "Actualizar stock (positivo=entrada, negativo=salida)") @PatchMapping("/{id}/stock")
    public ResponseEntity<MedicamentoDTO> stock(@PathVariable Long id, @RequestParam Integer cantidad) {
        return ResponseEntity.ok(service.actualizarStock(id, cantidad));
    }
    @Operation(summary = "Eliminar medicamento (baja lógica)") @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarLogico(id); return ResponseEntity.noContent().build();
    }
}
