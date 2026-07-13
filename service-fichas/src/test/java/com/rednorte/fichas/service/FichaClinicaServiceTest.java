package com.rednorte.fichas.service;
import com.rednorte.fichas.dto.FichaClinicaDTO;
import com.rednorte.fichas.model.FichaClinica;
import com.rednorte.fichas.repository.FichaClinicaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class FichaClinicaServiceTest {
    @Mock private FichaClinicaRepository repository;
    @Mock private WebClient.Builder webClientBuilder;
    @InjectMocks private FichaClinicaService service;
    private FichaClinica ficha;
    @BeforeEach
    void setUp() {
        ficha = new FichaClinica();
        ficha.setId(1L); ficha.setPacienteId(1L); ficha.setMedicoId(1L);
        ficha.setFecha("2025-06-01"); ficha.setDiagnostico("Hipertensión"); ficha.setActivo(true);
    }
    @Test
    void listar_debeRetornarFichas() {
        when(repository.findByActivoTrue()).thenReturn(List.of(ficha));
        List<FichaClinicaDTO> result = service.listar();
        assertEquals(1, result.size());
        assertEquals("Hipertensión", result.get(0).getDiagnostico());
    }
    @Test
    void porPaciente_debeRetornarFichasDePaciente() {
        when(repository.findByPacienteIdAndActivoTrue(1L)).thenReturn(List.of(ficha));
        List<FichaClinicaDTO> result = service.porPaciente(1L);
        assertEquals(1, result.size());
    }
    @Test
    void eliminarLogico_debeCambiarActivo() {
        when(repository.findById(1L)).thenReturn(Optional.of(ficha));
        when(repository.save(any())).thenReturn(ficha);
        service.eliminarLogico(1L);
        assertFalse(ficha.isActivo());
    }
}
