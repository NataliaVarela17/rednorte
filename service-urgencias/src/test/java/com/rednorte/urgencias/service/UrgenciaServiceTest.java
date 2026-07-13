package com.rednorte.urgencias.service;
import com.rednorte.urgencias.dto.UrgenciaDTO;
import com.rednorte.urgencias.model.AtencionUrgencia;
import com.rednorte.urgencias.repository.UrgenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class UrgenciaServiceTest {
    @Mock private UrgenciaRepository repository;
    @Mock private WebClient.Builder webClientBuilder;
    @InjectMocks private UrgenciaService service;
    private AtencionUrgencia urgencia;
    @BeforeEach void setUp() {
        urgencia = new AtencionUrgencia(); urgencia.setId(1L); urgencia.setPacienteId(1L);
        urgencia.setCentroId(1L); urgencia.setFecha("2025-07-10"); urgencia.setHora("22:15");
        urgencia.setPrioridad("ALTA"); urgencia.setMotivoConsulta("Dolor");
        urgencia.setEstado("EN_ESPERA"); urgencia.setActivo(true);
    }
    @Test void listar_debeRetornarUrgencias() {
        when(repository.findByActivoTrue()).thenReturn(List.of(urgencia));
        List<UrgenciaDTO> result = service.listar();
        assertEquals(1, result.size());
        assertEquals("EN_ESPERA", result.get(0).getEstado());
    }
    @Test void atender_debeActualizarEstado() {
        when(repository.findById(1L)).thenReturn(Optional.of(urgencia));
        when(repository.save(any())).thenReturn(urgencia);
        service.atender(1L, "Infarto agudo leve");
        assertEquals("ATENDIDO", urgencia.getEstado());
    }
    @Test void cerrar_debeDesactivar() {
        when(repository.findById(1L)).thenReturn(Optional.of(urgencia));
        when(repository.save(any())).thenReturn(urgencia);
        service.cerrar(1L);
        assertFalse(urgencia.isActivo());
    }
}
