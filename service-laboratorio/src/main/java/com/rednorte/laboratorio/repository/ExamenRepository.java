package com.rednorte.laboratorio.repository;
import com.rednorte.laboratorio.model.ExamenLaboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ExamenRepository extends JpaRepository<ExamenLaboratorio, Long> {
    List<ExamenLaboratorio> findByActivoTrue();
    List<ExamenLaboratorio> findByPacienteIdAndActivoTrue(Long pacienteId);
    List<ExamenLaboratorio> findByEstadoAndActivoTrue(String estado);
}
