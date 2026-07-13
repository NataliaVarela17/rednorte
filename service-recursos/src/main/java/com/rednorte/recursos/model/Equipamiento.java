package com.rednorte.recursos.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity @Table(name = "equipamiento") @Data
public class Equipamiento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(name = "sala_id")
    private Long salaId;
    @Column(length = 20)
    private String estado = "OPERATIVO";
    private boolean activo = true;
}
