package com.rednorte.notificaciones.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data @Schema(description = "Notificación del sistema")
public class NotificacionDTO {
    private Long id;
    @NotNull @Schema(example = "1") private Long destinatarioId;
    @NotBlank @Schema(example = "CITA") private String tipo;
    @NotBlank @Schema(example = "Recordatorio de cita") private String titulo;
    @NotBlank @Schema(example = "Tiene una cita programada mañana a las 09:00") private String mensaje;
    @NotBlank @Schema(example = "2025-07-14") private String fecha;
    private boolean leida;
}
