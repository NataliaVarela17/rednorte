package com.rednorte.centros.repository;
import com.rednorte.centros.model.CentroMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CentroMedicoRepository extends JpaRepository<CentroMedico, Long> {
    List<CentroMedico> findByActivoTrue();
    List<CentroMedico> findByComuna(String comuna);
    List<CentroMedico> findByTipo(String tipo);
}
