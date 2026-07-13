package com.rednorte.medicos.repository;
import com.rednorte.medicos.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findByActivoTrue();
    Optional<Medico> findByRut(String rut);
    List<Medico> findByEspecialidadAndActivoTrue(String especialidad);
    List<Medico> findByCentroIdAndActivoTrue(Long centroId);
}
