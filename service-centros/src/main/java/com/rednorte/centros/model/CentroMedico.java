package com.rednorte.centros.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "centros_salud")
@Data
public class CentroMedico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 100)
    private String nombre;
    @Column(nullable = false, length = 150)
    private String direccion;
    @Column(length = 20)
    private String telefono;
    @Column(length = 100)
    private String comuna;
    @Column(nullable = false, length = 50)
    private String tipo = "HOSPITAL";
    private boolean activo = true;
}
