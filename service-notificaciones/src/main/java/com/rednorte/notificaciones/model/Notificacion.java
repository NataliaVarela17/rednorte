package com.rednorte.notificaciones.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity @Table(name = "notificaciones") @Data
public class Notificacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "destinatario_id", nullable = false) private Long destinatarioId;
    @Column(nullable = false, length = 20) private String tipo;
    @Column(nullable = false, length = 200) private String titulo;
    @Column(nullable = false, columnDefinition = "TEXT") private String mensaje;
    @Column(nullable = false, length = 10) private String fecha;
    @Column(nullable = false) private boolean leida = false;
    private boolean activo = true;
}
