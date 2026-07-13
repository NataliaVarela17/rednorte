package com.rednorte.fichas.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data @Schema(description = "Ficha clínica del paciente")
public class FichaClinicaDTO {
    private Long id;
    @NotNull @Schema(example = "1") private Long pacienteId;
    @NotNull @Schema(example = "1") private Long medicoId;
    private Long citaId;
    @NotBlank @Schema(example = "2025-07-15") private String fecha;
    @NotBlank @Schema(example = "Hipertensión arterial leve") private String diagnostico;
    private String tratamiento;
    private String observaciones;
}
