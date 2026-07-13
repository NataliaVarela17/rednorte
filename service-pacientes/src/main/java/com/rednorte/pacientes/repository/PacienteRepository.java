package com.rednorte.pacientes.repository;
import com.rednorte.pacientes.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByActivoTrue();
    Optional<Paciente> findByRut(String rut);
}
