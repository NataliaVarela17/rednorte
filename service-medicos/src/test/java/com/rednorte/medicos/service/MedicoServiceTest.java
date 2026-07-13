package com.rednorte.medicos.service;

import com.rednorte.medicos.dto.MedicoDTO;
import com.rednorte.medicos.model.Medico;
import com.rednorte.medicos.repository.MedicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicoServiceTest {
    @Mock private MedicoRepository repository;
    @InjectMocks private MedicoService service;

    private Medico medico;

    @BeforeEach
    void setUp() {
        medico = new Medico();
        medico.setId(1L); medico.setRut("11222333-4");
        medico.setNombre("Ana"); medico.setApellido("Martínez");
        medico.setEspecialidad("Cardiología"); medico.setCentroId(1L);
        medico.setActivo(true);
    }

    @Test
    void listarActivos_debeRetornarMedicos() {
        when(repository.findByActivoTrue()).thenReturn(List.of(medico));
        List<MedicoDTO> result = service.listarActivos();
        assertEquals(1, result.size());
        assertEquals("Cardiología", result.get(0).getEspecialidad());
    }

    @Test
    void registrar_rutDuplicado_debeLanzarExcepcion() {
        when(repository.findByRut("11222333-4")).thenReturn(Optional.of(medico));
        MedicoDTO dto = new MedicoDTO();
        dto.setRut("11222333-4"); dto.setNombre("X"); dto.setApellido("Y");
        dto.setEspecialidad("Z"); dto.setCentroId(1L);
        assertThrows(RuntimeException.class, () -> service.registrar(dto));
    }

    @Test
    void porEspecialidad_debeRetornarFiltrado() {
        when(repository.findByEspecialidadAndActivoTrue("Cardiología")).thenReturn(List.of(medico));
        List<MedicoDTO> result = service.porEspecialidad("Cardiología");
        assertEquals(1, result.size());
    }

    @Test
    void desactivar_debePonerActivoFalse() {
        when(repository.findById(1L)).thenReturn(Optional.of(medico));
        when(repository.save(any())).thenReturn(medico);
        service.desactivar(1L);
        assertFalse(medico.isActivo());
    }
}
