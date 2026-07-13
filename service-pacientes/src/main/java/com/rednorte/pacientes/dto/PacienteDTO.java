package com.rednorte.pacientes.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data @Schema(description = "DTO del paciente")
public class PacienteDTO {
    private Long id;
    @NotBlank(message = "El RUT es obligatorio") @Schema(example = "12345678-9")
    private String rut;
    @NotBlank(message = "El nombre es obligatorio") @Schema(example = "Juan")
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio") @Schema(example = "Pérez")
    private String apellido;
    @Schema(example = "987654321")
    private String telefono;
    @Email(message = "Email inválido") @Schema(example = "juan@email.com")
    private String email;
    private String direccion;
    private String fechaNacimiento;
}
