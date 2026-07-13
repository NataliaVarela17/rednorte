package com.rednorte.pacientes.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity @Table(name = "pacientes") @Data
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 12)
    private String rut;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, length = 100)
    private String apellido;
    @Column(length = 20)
    private String telefono;
    @Column(length = 150)
    private String email;
    @Column(length = 200)
    private String direccion;
    @Column(name = "fecha_nacimiento", length = 10)
    private String fechaNacimiento;
    private boolean activo = true;
}
