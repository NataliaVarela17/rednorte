package com.rednorte.notificaciones.service;
import com.rednorte.notificaciones.dto.NotificacionDTO;
import com.rednorte.notificaciones.model.Notificacion;
import com.rednorte.notificaciones.repository.NotificacionRepository;
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
class NotificacionServiceTest {
    @Mock private NotificacionRepository repository;
    @InjectMocks private NotificacionService service;
    private Notificacion notif;
    @BeforeEach void setUp() {
        notif = new Notificacion(); notif.setId(1L); notif.setDestinatarioId(1L);
        notif.setTipo("CITA"); notif.setTitulo("Recordatorio"); notif.setMensaje("Cita mañana");
        notif.setFecha("2025-07-14"); notif.setLeida(false); notif.setActivo(true);
    }
    @Test void listar_debeRetornarNotificaciones() {
        when(repository.findByActivoTrue()).thenReturn(List.of(notif));
        assertEquals(1, service.listar().size());
    }
    @Test void noLeidas_debeRetornarSoloNoLeidas() {
        when(repository.findByDestinatarioIdAndLeidaFalseAndActivoTrue(1L)).thenReturn(List.of(notif));
        List<NotificacionDTO> result = service.noLeidas(1L);
        assertEquals(1, result.size());
        assertFalse(result.get(0).isLeida());
    }
    @Test void marcarLeida_debeCambiarEstado() {
        when(repository.findById(1L)).thenReturn(Optional.of(notif));
        when(repository.save(any())).thenReturn(notif);
        service.marcarLeida(1L);
        assertTrue(notif.isLeida());
    }
    @Test void enviar_debePersistirNotificacion() {
        NotificacionDTO dto = new NotificacionDTO(); dto.setDestinatarioId(2L);
        dto.setTipo("EXAMEN"); dto.setTitulo("Nuevo"); dto.setMensaje("Msg"); dto.setFecha("2025-07-15");
        when(repository.save(any())).thenReturn(notif);
        NotificacionDTO result = service.enviar(dto);
        assertNotNull(result);
    }
}
