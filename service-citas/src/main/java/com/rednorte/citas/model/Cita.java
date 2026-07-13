package com.rednorte.citas.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity @Table(name = "citas") @Data
public class Cita {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;
    @Column(name = "medico_id", nullable = false)
    private Long medicoId;
    @Column(name = "centro_id", nullable = false)
    private Long centroId;
    @Column(nullable = false, length = 10)
    private String fecha;
    @Column(nullable = false, length = 5)
    private String hora;
    @Column(length = 20)
    private String estado = "PENDIENTE";
    @Column(length = 200)
    private String observaciones;
    private boolean activo = true;
}
