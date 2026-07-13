package com.rednorte.medicos.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity @Table(name = "medicos") @Data
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 12)
    private String rut;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, length = 100)
    private String apellido;
    @Column(nullable = false, length = 100)
    private String especialidad;
    @Column(name = "centro_id", nullable = false)
    private Long centroId;
    @Column(length = 20)
    private String telefono;
    @Column(length = 150)
    private String email;
    private boolean activo = true;
}
