package com.rednorte.pacientes.service;

import com.rednorte.pacientes.dto.PacienteDTO;
import com.rednorte.pacientes.model.Paciente;
import com.rednorte.pacientes.repository.PacienteRepository;
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
class PacienteServiceTest {
    @Mock private PacienteRepository repository;
    @InjectMocks private PacienteService service;

    private Paciente paciente;

    @BeforeEach
    void setUp() {
        paciente = new Paciente();
        paciente.setId(1L);
        paciente.setRut("12345678-9");
        paciente.setNombre("Carlos");
        paciente.setApellido("González");
        paciente.setActivo(true);
    }

    @Test
    void listarActivos_debeRetornarLista() {
        when(repository.findByActivoTrue()).thenReturn(List.of(paciente));
        List<PacienteDTO> result = service.listarActivos();
        assertEquals(1, result.size());
        assertEquals("Carlos", result.get(0).getNombre());
    }

    @Test
    void registrar_rutDuplicado_debeLanzarExcepcion() {
        when(repository.findByRut("12345678-9")).thenReturn(Optional.of(paciente));
        PacienteDTO dto = new PacienteDTO();
        dto.setRut("12345678-9");
        dto.setNombre("Otro");
        dto.setApellido("Apellido");
        assertThrows(RuntimeException.class, () -> service.registrar(dto));
    }

    @Test
    void registrar_rutNuevo_debeGuardar() {
        when(repository.findByRut("11111111-1")).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(paciente);
        PacienteDTO dto = new PacienteDTO();
        dto.setRut("11111111-1"); dto.setNombre("Ana"); dto.setApellido("Ruiz");
        PacienteDTO result = service.registrar(dto);
        assertNotNull(result);
    }

    @Test
    void eliminarLogico_debeDesactivar() {
        when(repository.findById(1L)).thenReturn(Optional.of(paciente));
        when(repository.save(any())).thenReturn(paciente);
        service.eliminarLogico(1L);
        assertFalse(paciente.isActivo());
    }
}
