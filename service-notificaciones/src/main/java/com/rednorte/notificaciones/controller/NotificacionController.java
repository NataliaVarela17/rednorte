package com.rednorte.notificaciones.controller;
import com.rednorte.notificaciones.dto.NotificacionDTO;
import com.rednorte.notificaciones.service.NotificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/notificaciones")
@Tag(name = "Notificaciones", description = "Sistema de notificaciones RedNorte")
public class NotificacionController {
    @Autowired private NotificacionService service;
    @Operation(summary = "Listar todas las notificaciones") @GetMapping
    public ResponseEntity<List<NotificacionDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    @Operation(summary = "Notificaciones de un destinatario") @GetMapping("/destinatario/{id}")
    public ResponseEntity<List<NotificacionDTO>> porDestinatario(@PathVariable Long id) {
        return ResponseEntity.ok(service.porDestinatario(id));
    }
    @Operation(summary = "Notificaciones no leídas") @GetMapping("/destinatario/{id}/no-leidas")
    public ResponseEntity<List<NotificacionDTO>> noLeidas(@PathVariable Long id) {
        return ResponseEntity.ok(service.noLeidas(id));
    }
    @Operation(summary = "Enviar notificación") @PostMapping
    public ResponseEntity<NotificacionDTO> enviar(@Valid @RequestBody NotificacionDTO dto) {
        return new ResponseEntity<>(service.enviar(dto), HttpStatus.CREATED);
    }
    @Operation(summary = "Marcar como leída") @PatchMapping("/{id}/leer")
    public ResponseEntity<NotificacionDTO> marcarLeida(@PathVariable Long id) {
        return ResponseEntity.ok(service.marcarLeida(id));
    }
    @Operation(summary = "Eliminar notificación (baja lógica)") @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarLogico(id); return ResponseEntity.noContent().build();
    }
}
