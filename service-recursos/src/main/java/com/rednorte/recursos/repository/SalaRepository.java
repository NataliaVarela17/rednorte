package com.rednorte.recursos.repository;
import com.rednorte.recursos.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
    List<Sala> findByDisponibleTrueAndActivoTrue();
    List<Sala> findByActivoTrue();
    List<Sala> findByCentroIdAndActivoTrue(Long centroId);
    boolean existsByCodigo(String codigo);
}
