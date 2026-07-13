package com.rednorte.recursos.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity @Table(name = "salas") @Data
public class Sala {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String codigo;
    @Column(nullable = false, length = 50)
    private String tipo;
    @Column(name = "centro_id", nullable = false)
    private Long centroId;
    private boolean disponible = true;
    private boolean activo = true;
}
