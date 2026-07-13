package com.rednorte.recursos.repository;
import com.rednorte.recursos.model.Equipamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface EquipamientoRepository extends JpaRepository<Equipamiento, Long> {
    List<Equipamiento> findByActivoTrue();
    List<Equipamiento> findBySalaIdAndActivoTrue(Long salaId);
}
