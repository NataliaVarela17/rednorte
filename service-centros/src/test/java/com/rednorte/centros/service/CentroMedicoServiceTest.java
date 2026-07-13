package com.rednorte.centros.service;

import com.rednorte.centros.dto.CentroDTO;
import com.rednorte.centros.model.CentroMedico;
import com.rednorte.centros.repository.CentroMedicoRepository;
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
class CentroMedicoServiceTest {

    @Mock
    private CentroMedicoRepository repository;

    @InjectMocks
    private CentroMedicoService service;

    private CentroMedico centro;

    @BeforeEach
    void setUp() {
        centro = new CentroMedico();
        centro.setId(1L);
        centro.setNombre("Hospital Norte");
        centro.setDireccion("Av. Test 123");
        centro.setTelefono("555123456");
        centro.setComuna("Antofagasta");
        centro.setTipo("HOSPITAL");
        centro.setActivo(true);
    }

    @Test
    void obtenerTodosActivos_debeRetornarListaDeActivos() {
        when(repository.findByActivoTrue()).thenReturn(List.of(centro));
        List<CentroDTO> result = service.obtenerTodosActivos();
        assertFalse(result.isEmpty());
        assertEquals("Hospital Norte", result.get(0).getNombre());
        verify(repository).findByActivoTrue();
    }

    @Test
    void obtenerPorId_cuandoExiste_debeRetornarDTO() {
        when(repository.findById(1L)).thenReturn(Optional.of(centro));
        Optional<CentroDTO> result = service.obtenerPorId(1L);
        assertTrue(result.isPresent());
        assertEquals("Hospital Norte", result.get().getNombre());
    }

    @Test
    void obtenerPorId_cuandoNoExiste_debeRetornarVacio() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        Optional<CentroDTO> result = service.obtenerPorId(99L);
        assertTrue(result.isEmpty());
    }

    @Test
    void registrar_debeGuardarYRetornarDTO() {
        CentroDTO dto = new CentroDTO();
        dto.setNombre("Nuevo Centro");
        dto.setDireccion("Calle Nueva 1");
        dto.setComuna("Calama");
        dto.setTipo("CESFAM");

        CentroMedico saved = new CentroMedico();
        saved.setId(2L);
        saved.setNombre("Nuevo Centro");
        saved.setDireccion("Calle Nueva 1");
        saved.setComuna("Calama");
        saved.setTipo("CESFAM");
        saved.setActivo(true);

        when(repository.save(any(CentroMedico.class))).thenReturn(saved);

        CentroDTO result = service.registrar(dto);
        assertNotNull(result);
        assertEquals("Nuevo Centro", result.getNombre());
    }

    @Test
    void desactivar_cuandoExiste_debeDesactivar() {
        when(repository.findById(1L)).thenReturn(Optional.of(centro));
        when(repository.save(any(CentroMedico.class))).thenReturn(centro);
        service.desactivar(1L);
        assertFalse(centro.isActivo());
        verify(repository).save(centro);
    }

    @Test
    void desactivar_cuandoNoExiste_debeLanzarExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.desactivar(99L));
    }
}
