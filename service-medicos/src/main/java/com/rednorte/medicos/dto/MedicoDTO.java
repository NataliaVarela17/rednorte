package com.rednorte.medicos.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data @Schema(description = "DTO del médico")
public class MedicoDTO {
    private Long id;
    @NotBlank(message = "RUT obligatorio") @Schema(example = "11222333-4")
    private String rut;
    @NotBlank(message = "Nombre obligatorio") @Schema(example = "Ana")
    private String nombre;
    @NotBlank(message = "Apellido obligatorio") @Schema(example = "Martínez")
    private String apellido;
    @NotBlank(message = "Especialidad obligatoria") @Schema(example = "Cardiología")
    private String especialidad;
    @NotNull(message = "Centro obligatorio") @Schema(example = "1")
    private Long centroId;
    private String telefono;
    @Email @Schema(example = "ana@rednorte.cl")
    private String email;
}
