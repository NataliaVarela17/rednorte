package com.rednorte.recursos.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data @Schema(description = "DTO para salas / pabellones")
public class SalaDTO {
    private Long id;
    @NotBlank(message = "El código es obligatorio") @Schema(example = "SALA-01")
    private String codigo;
    @NotBlank(message = "El tipo es obligatorio") @Schema(example = "CONSULTA")
    private String tipo;
    @NotNull(message = "El ID del centro es obligatorio") @Schema(example = "1")
    private Long centroId;
    private boolean disponible;
}
