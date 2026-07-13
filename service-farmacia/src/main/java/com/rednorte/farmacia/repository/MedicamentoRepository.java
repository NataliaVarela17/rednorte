package com.rednorte.farmacia.repository;
import com.rednorte.farmacia.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByActivoTrue();
    List<Medicamento> findByCentroIdAndActivoTrue(Long centroId);
    List<Medicamento> findByStockLessThanAndActivoTrue(Integer stock);
}
