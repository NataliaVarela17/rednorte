package com.rednorte.citas.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data @Schema(description = "DTO para agendar citas médicas")
public class CitaDTO {
    private Long id;
    @NotNull(message = "ID de paciente obligatorio") @Schema(example = "1")
    private Long pacienteId;
    @NotNull(message = "ID de médico obligatorio") @Schema(example = "1")
    private Long medicoId;
    @NotNull(message = "ID de centro obligatorio") @Schema(example = "1")
    private Long centroId;
    @NotBlank(message = "Fecha obligatoria") @Schema(example = "2025-07-15")
    private String fecha;
    @NotBlank(message = "Hora obligatoria") @Schema(example = "10:30")
    private String hora;
    @Schema(example = "PENDIENTE")
    private String estado;
    private String observaciones;
    // Campos enriquecidos (traídos desde otros servicios)
    private String nombrePaciente;
    private String nombreMedico;
    private String nombreCentro;
}
