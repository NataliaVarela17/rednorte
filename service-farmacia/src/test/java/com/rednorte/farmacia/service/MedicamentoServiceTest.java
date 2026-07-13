package com.rednorte.farmacia.service;
import com.rednorte.farmacia.dto.MedicamentoDTO;
import com.rednorte.farmacia.model.Medicamento;
import com.rednorte.farmacia.repository.MedicamentoRepository;
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
class MedicamentoServiceTest {
    @Mock private MedicamentoRepository repository;
    @InjectMocks private MedicamentoService service;
    private Medicamento med;
    @BeforeEach void setUp() {
        med = new Medicamento(); med.setId(1L); med.setNombre("Amoxicilina 500mg");
        med.setPrincipioActivo("Amoxicilina"); med.setPresentacion("Cápsula");
        med.setStock(8); med.setStockMinimo(15); med.setCentroId(1L); med.setActivo(true);
    }
    @Test void listar_debeRetornarMedicamentos() {
        when(repository.findByActivoTrue()).thenReturn(List.of(med));
        assertEquals(1, service.listar().size());
    }
    @Test void stockBajo_debeDetectarStockInsuficiente() {
        when(repository.findByActivoTrue()).thenReturn(List.of(med));
        List<MedicamentoDTO> result = service.stockBajo();
        assertEquals(1, result.size()); // stock(8) < stockMinimo(15)
    }
    @Test void actualizarStock_debeIncrementar() {
        when(repository.findById(1L)).thenReturn(Optional.of(med));
        when(repository.save(any())).thenReturn(med);
        service.actualizarStock(1L, 50);
        assertEquals(58, med.getStock());
    }
    @Test void actualizarStock_sinSuficiente_debeLanzarExcepcion() {
        when(repository.findById(1L)).thenReturn(Optional.of(med));
        assertThrows(RuntimeException.class, () -> service.actualizarStock(1L, -100));
    }
}
