package com.rednorte.centros.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
@Schema(description = "DTO para crear o consultar un centro de salud")
public class CentroDTO {
    private Long id;
    @NotBlank(message = "El nombre del centro es obligatorio")
    @Size(max = 100)
    @Schema(example = "Hospital Regional Norte")
    private String nombre;
    @NotBlank(message = "La dirección es obligatoria")
    @Schema(example = "Av. Principal 1234")
    private String direccion;
    @Schema(example = "222333444")
    private String telefono;
    @Schema(example = "Antofagasta")
    private String comuna;
    @Schema(example = "HOSPITAL")
    private String tipo;
}
