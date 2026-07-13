package com.rednorte.farmacia.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data @Schema(description = "Medicamento en farmacia")
public class MedicamentoDTO {
    private Long id;
    @NotBlank @Schema(example = "Losartán 50mg") private String nombre;
    @NotBlank @Schema(example = "Losartán potásico") private String principioActivo;
    @NotBlank @Schema(example = "Comprimido 50mg") private String presentacion;
    @NotNull @Min(0) @Schema(example = "100") private Integer stock;
    @Schema(example = "10") private Integer stockMinimo;
    @NotNull @Schema(example = "1") private Long centroId;
}
