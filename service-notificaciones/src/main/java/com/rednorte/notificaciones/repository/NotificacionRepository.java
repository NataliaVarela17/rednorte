package com.rednorte.notificaciones.repository;
import com.rednorte.notificaciones.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByActivoTrue();
    List<Notificacion> findByDestinatarioIdAndActivoTrue(Long destinatarioId);
    List<Notificacion> findByDestinatarioIdAndLeidaFalseAndActivoTrue(Long destinatarioId);
}
