package com.rednorte.notificaciones.service;
import com.rednorte.notificaciones.dto.NotificacionDTO;
import com.rednorte.notificaciones.model.Notificacion;
import com.rednorte.notificaciones.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class NotificacionService {
    @Autowired private NotificacionRepository repository;
    private NotificacionDTO toDto(Notificacion n) {
        NotificacionDTO dto = new NotificacionDTO();
        dto.setId(n.getId()); dto.setDestinatarioId(n.getDestinatarioId());
        dto.setTipo(n.getTipo()); dto.setTitulo(n.getTitulo());
        dto.setMensaje(n.getMensaje()); dto.setFecha(n.getFecha());
        dto.setLeida(n.isLeida()); return dto;
    }
    public List<NotificacionDTO> listar() { return repository.findByActivoTrue().stream().map(this::toDto).collect(Collectors.toList()); }
    public List<NotificacionDTO> porDestinatario(Long id) { return repository.findByDestinatarioIdAndActivoTrue(id).stream().map(this::toDto).collect(Collectors.toList()); }
    public List<NotificacionDTO> noLeidas(Long id) { return repository.findByDestinatarioIdAndLeidaFalseAndActivoTrue(id).stream().map(this::toDto).collect(Collectors.toList()); }
    public NotificacionDTO enviar(NotificacionDTO dto) {
        Notificacion n = new Notificacion();
        n.setDestinatarioId(dto.getDestinatarioId()); n.setTipo(dto.getTipo());
        n.setTitulo(dto.getTitulo()); n.setMensaje(dto.getMensaje());
        n.setFecha(dto.getFecha()); n.setLeida(false); n.setActivo(true);
        return toDto(repository.save(n));
    }
    public NotificacionDTO marcarLeida(Long id) {
        Notificacion n = repository.findById(id).orElseThrow(() -> new RuntimeException("Notificación no encontrada: " + id));
        n.setLeida(true); return toDto(repository.save(n));
    }
    public void eliminarLogico(Long id) {
        Notificacion n = repository.findById(id).orElseThrow(() -> new RuntimeException("Notificación no encontrada: " + id));
        n.setActivo(false); repository.save(n);
    }
}
