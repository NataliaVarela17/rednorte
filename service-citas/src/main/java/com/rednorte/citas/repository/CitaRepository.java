package com.rednorte.citas.repository;
import com.rednorte.citas.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByActivoTrue();
    List<Cita> findByPacienteIdAndActivoTrue(Long pacienteId);
    List<Cita> findByMedicoIdAndActivoTrue(Long medicoId);
    List<Cita> findByFechaAndActivoTrue(String fecha);
}
