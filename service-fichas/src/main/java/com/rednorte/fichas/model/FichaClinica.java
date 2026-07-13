package com.rednorte.fichas.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity @Table(name = "fichas_clinicas") @Data
public class FichaClinica {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;
    @Column(name = "medico_id", nullable = false)
    private Long medicoId;
    @Column(name = "cita_id")
    private Long citaId;
    @Column(nullable = false, length = 10)
    private String fecha;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String diagnostico;
    @Column(columnDefinition = "TEXT")
    private String tratamiento;
    @Column(columnDefinition = "TEXT")
    private String observaciones;
    private boolean activo = true;
}
