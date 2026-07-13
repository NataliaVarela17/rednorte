package com.rednorte.farmacia.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity @Table(name = "medicamentos") @Data
public class Medicamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 100)
    private String nombre;
    @Column(nullable = false, length = 100)
    private String principioActivo;
    @Column(nullable = false, length = 50)
    private String presentacion;
    @Column(nullable = false)
    private Integer stock = 0;
    @Column(name = "stock_minimo")
    private Integer stockMinimo = 10;
    @Column(name = "centro_id", nullable = false)
    private Long centroId;
    private boolean activo = true;
}
