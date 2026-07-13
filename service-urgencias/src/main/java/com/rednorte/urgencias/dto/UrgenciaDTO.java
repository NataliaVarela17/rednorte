package com.rednorte.urgencias.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data @Schema(description = "Atención de urgencia")
public class UrgenciaDTO {
    private Long id;
    @NotNull @Schema(example = "1") private Long pacienteId;
    @NotNull @Schema(example = "1") private Long centroId;
    @NotBlank @Schema(example = "2025-07-15") private String fecha;
    @NotBlank @Schema(example = "14:30") private String hora;
    @Schema(example = "ALTA") private String prioridad;
    @NotBlank @Schema(example = "Dolor torácico agudo") private String motivoConsulta;
    private String diagnostico;
    @Schema(example = "EN_ESPERA") private String estado;
}
