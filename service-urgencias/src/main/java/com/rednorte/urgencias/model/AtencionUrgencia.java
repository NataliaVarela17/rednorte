package com.rednorte.urgencias.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity @Table(name = "atenciones_urgencia") @Data
public class AtencionUrgencia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "paciente_id", nullable = false) private Long pacienteId;
    @Column(name = "centro_id", nullable = false) private Long centroId;
    @Column(nullable = false, length = 10) private String fecha;
    @Column(nullable = false, length = 5) private String hora;
    @Column(nullable = false, length = 20) private String prioridad = "MEDIA";
    @Column(nullable = false, columnDefinition = "TEXT") private String motivoConsulta;
    @Column(columnDefinition = "TEXT") private String diagnostico;
    @Column(length = 20) private String estado = "EN_ESPERA";
    private boolean activo = true;
}
