package com.rednorte.recursos.service;

import com.rednorte.recursos.dto.EquipamientoDTO;
import com.rednorte.recursos.dto.SalaDTO;
import com.rednorte.recursos.model.Equipamiento;
import com.rednorte.recursos.model.Sala;
import com.rednorte.recursos.repository.EquipamientoRepository;
import com.rednorte.recursos.repository.SalaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecursoServiceTest {

    @Mock private SalaRepository salaRepo;
    @Mock private EquipamientoRepository equipRepo;
    @InjectMocks private RecursoService service;

    private Sala sala;
    private Equipamiento equipo;

    @BeforeEach
    void setUp() {
        sala = new Sala();
        sala.setId(1L); sala.setCodigo("SALA-01");
        sala.setTipo("CONSULTA"); sala.setCentroId(1L);
        sala.setDisponible(true); sala.setActivo(true);

        equipo = new Equipamiento();
        equipo.setId(1L); equipo.setNombre("Electrocardiograma");
        equipo.setSalaId(1L); equipo.setEstado("OPERATIVO"); equipo.setActivo(true);
    }

    @Test
    void obtenerSalasDisponibles_debeRetornarSolasDisponibles() {
        when(salaRepo.findByDisponibleTrueAndActivoTrue()).thenReturn(List.of(sala));
        List<SalaDTO> result = service.obtenerSalasDisponibles();
        assertEquals(1, result.size());
        assertEquals("SALA-01", result.get(0).getCodigo());
        assertTrue(result.get(0).isDisponible());
    }

    @Test
    void obtenerTodasSalas_debeRetornarActivas() {
        when(salaRepo.findByActivoTrue()).thenReturn(List.of(sala));
        List<SalaDTO> result = service.obtenerTodasSalas();
        assertEquals(1, result.size());
    }

    @Test
    void crearSala_codigoNuevo_debeGuardar() {
        SalaDTO dto = new SalaDTO();
        dto.setCodigo("SALA-05"); dto.setTipo("UCI"); dto.setCentroId(1L);
        when(salaRepo.existsByCodigo("SALA-05")).thenReturn(false);
        when(salaRepo.save(any())).thenReturn(sala);
        SalaDTO result = service.crearSala(dto);
        assertNotNull(result);
        verify(salaRepo).save(any(Sala.class));
    }

    @Test
    void crearSala_codigoDuplicado_debeLanzarExcepcion() {
        SalaDTO dto = new SalaDTO();
        dto.setCodigo("SALA-01"); dto.setTipo("UCI"); dto.setCentroId(1L);
        when(salaRepo.existsByCodigo("SALA-01")).thenReturn(true);
        assertThrows(RuntimeException.class, () -> service.crearSala(dto));
    }

    @Test
    void desactivarSala_debePonerActivoFalse() {
        when(salaRepo.findById(1L)).thenReturn(Optional.of(sala));
        when(salaRepo.save(any())).thenReturn(sala);
        service.desactivarSala(1L);
        assertFalse(sala.isActivo());
    }

    @Test
    void obtenerEquipamiento_debeRetornarActivos() {
        when(equipRepo.findByActivoTrue()).thenReturn(List.of(equipo));
        List<EquipamientoDTO> result = service.obtenerEquipamiento();
        assertEquals(1, result.size());
        assertEquals("Electrocardiograma", result.get(0).getNombre());
    }

    @Test
    void actualizarEstadoEquipo_debeActualizarEstado() {
        when(equipRepo.findById(1L)).thenReturn(Optional.of(equipo));
        when(equipRepo.save(any())).thenReturn(equipo);
        service.actualizarEstadoEquipo(1L, "MANTENIMIENTO");
        assertEquals("MANTENIMIENTO", equipo.getEstado());
    }
}
