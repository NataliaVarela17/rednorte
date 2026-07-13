package com.rednorte.fichas.repository;
import com.rednorte.fichas.model.FichaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface FichaClinicaRepository extends JpaRepository<FichaClinica, Long> {
    List<FichaClinica> findByPacienteIdAndActivoTrue(Long pacienteId);
    List<FichaClinica> findByMedicoIdAndActivoTrue(Long medicoId);
    List<FichaClinica> findByActivoTrue();
}
