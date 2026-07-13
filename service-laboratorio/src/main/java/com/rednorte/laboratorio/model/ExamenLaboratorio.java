package com.rednorte.laboratorio.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity @Table(name = "examenes_laboratorio") @Data
public class ExamenLaboratorio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "paciente_id", nullable = false) private Long pacienteId;
    @Column(name = "medico_id", nullable = false) private Long medicoId;
    @Column(nullable = false, length = 100) private String tipoExamen;
    @Column(nullable = false, length = 10) private String fechaSolicitud;
    private String fechaResultado;
    @Column(columnDefinition = "TEXT") private String resultado;
    @Column(length = 20) private String estado = "SOLICITADO";
    private boolean activo = true;
}
