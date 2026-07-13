package com.rednorte.citas.service;

import com.rednorte.citas.dto.CitaDTO;
import com.rednorte.citas.model.Cita;
import com.rednorte.citas.repository.CitaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitaServiceTest {
    @Mock private CitaRepository repository;
    @Mock private WebClient.Builder webClientBuilder;
    @InjectMocks private CitaService service;

    private Cita cita;

    @BeforeEach
    void setUp() {
        cita = new Cita();
        cita.setId(1L); cita.setPacienteId(1L); cita.setMedicoId(1L);
        cita.setCentroId(1L); cita.setFecha("2025-07-15"); cita.setHora("09:00");
        cita.setEstado("PENDIENTE"); cita.setActivo(true);
    }

    @Test
    void listarActivas_debeRetornarCitas() {
        when(repository.findByActivoTrue()).thenReturn(List.of(cita));
        List<CitaDTO> result = service.listarActivas();
        assertEquals(1, result.size());
        assertEquals("PENDIENTE", result.get(0).getEstado());
    }

    @Test
    void agendar_debeCrearCita() {
        CitaDTO dto = new CitaDTO();
        dto.setPacienteId(1L); dto.setMedicoId(1L); dto.setCentroId(1L);
        dto.setFecha("2025-07-20"); dto.setHora("11:00");
        when(repository.save(any())).thenReturn(cita);
        CitaDTO result = service.agendar(dto);
        assertNotNull(result);
        verify(repository).save(any(Cita.class));
    }

    @Test
    void cambiarEstado_debeActualizarEstado() {
        when(repository.findById(1L)).thenReturn(Optional.of(cita));
        when(repository.save(any())).thenReturn(cita);
        service.cambiarEstado(1L, "COMPLETADA");
        assertEquals("COMPLETADA", cita.getEstado());
    }

    @Test
    void cancelar_debeDesactivar() {
        when(repository.findById(1L)).thenReturn(Optional.of(cita));
        when(repository.save(any())).thenReturn(cita);
        service.cancelar(1L);
        assertFalse(cita.isActivo());
        assertEquals("CANCELADA", cita.getEstado());
    }
}
