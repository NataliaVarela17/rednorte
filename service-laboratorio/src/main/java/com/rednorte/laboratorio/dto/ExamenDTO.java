package com.rednorte.laboratorio.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data @Schema(description = "Examen de laboratorio")
public class ExamenDTO {
    private Long id;
    @NotNull @Schema(example = "1") private Long pacienteId;
    @NotNull @Schema(example = "1") private Long medicoId;
    @NotBlank @Schema(example = "Hemograma completo") private String tipoExamen;
    @NotBlank @Schema(example = "2025-07-15") private String fechaSolicitud;
    private String fechaResultado;
    @Schema(example = "Hemoglobina: 14.2 g/dL (normal)") private String resultado;
    @Schema(example = "SOLICITADO") private String estado;
}
