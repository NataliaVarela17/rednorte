package com.rednorte.recursos.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class EquipamientoDTO {
    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    private Long salaId;
    private String estado;
}
