package com.rednorte.urgencias.repository;
import com.rednorte.urgencias.model.AtencionUrgencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UrgenciaRepository extends JpaRepository<AtencionUrgencia, Long> {
    List<AtencionUrgencia> findByActivoTrue();
    List<AtencionUrgencia> findByEstadoAndActivoTrue(String estado);
    List<AtencionUrgencia> findByCentroIdAndActivoTrue(Long centroId);
    List<AtencionUrgencia> findByPrioridadAndActivoTrue(String prioridad);
}
