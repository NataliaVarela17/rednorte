package com.rednorte.laboratorio.service;
import com.rednorte.laboratorio.dto.ExamenDTO;
import com.rednorte.laboratorio.model.ExamenLaboratorio;
import com.rednorte.laboratorio.repository.ExamenRepository;
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
class ExamenServiceTest {
    @Mock private ExamenRepository repository;
    @InjectMocks private ExamenService service;
    private ExamenLaboratorio examen;
    @BeforeEach void setUp() {
        examen = new ExamenLaboratorio(); examen.setId(1L); examen.setPacienteId(1L);
        examen.setMedicoId(1L); examen.setTipoExamen("Hemograma"); examen.setFechaSolicitud("2025-07-01");
        examen.setEstado("SOLICITADO"); examen.setActivo(true);
    }
    @Test void listar_debeRetornarExamenes() {
        when(repository.findByActivoTrue()).thenReturn(List.of(examen));
        assertEquals(1, service.listar().size());
    }
    @Test void solicitar_debeCrearEnEstadoSolicitado() {
        ExamenDTO dto = new ExamenDTO(); dto.setPacienteId(1L); dto.setMedicoId(1L);
        dto.setTipoExamen("Glucosa"); dto.setFechaSolicitud("2025-07-20");
        when(repository.save(any())).thenReturn(examen);
        ExamenDTO result = service.solicitar(dto);
        assertNotNull(result);
        verify(repository).save(any(ExamenLaboratorio.class));
    }
    @Test void ingresarResultado_debeCompletarExamen() {
        when(repository.findById(1L)).thenReturn(Optional.of(examen));
        when(repository.save(any())).thenReturn(examen);
        service.ingresarResultado(1L, "Normal", "2025-07-05");
        assertEquals("COMPLETADO", examen.getEstado());
    }
}
