package com.rednorte.farmacia.service;
import com.rednorte.farmacia.dto.MedicamentoDTO;
import com.rednorte.farmacia.model.Medicamento;
import com.rednorte.farmacia.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class MedicamentoService {
    @Autowired private MedicamentoRepository repository;
    private MedicamentoDTO toDto(Medicamento m) {
        MedicamentoDTO dto = new MedicamentoDTO();
        dto.setId(m.getId()); dto.setNombre(m.getNombre()); dto.setPrincipioActivo(m.getPrincipioActivo());
        dto.setPresentacion(m.getPresentacion()); dto.setStock(m.getStock());
        dto.setStockMinimo(m.getStockMinimo()); dto.setCentroId(m.getCentroId()); return dto;
    }
    public List<MedicamentoDTO> listar() { return repository.findByActivoTrue().stream().map(this::toDto).collect(Collectors.toList()); }
    public Optional<MedicamentoDTO> porId(Long id) { return repository.findById(id).map(this::toDto); }
    public List<MedicamentoDTO> porCentro(Long centroId) { return repository.findByCentroIdAndActivoTrue(centroId).stream().map(this::toDto).collect(Collectors.toList()); }
    public List<MedicamentoDTO> stockBajo() { return repository.findByActivoTrue().stream().filter(m -> m.getStock() <= m.getStockMinimo()).map(this::toDto).collect(Collectors.toList()); }
    public MedicamentoDTO registrar(MedicamentoDTO dto) {
        Medicamento m = new Medicamento();
        m.setNombre(dto.getNombre()); m.setPrincipioActivo(dto.getPrincipioActivo());
        m.setPresentacion(dto.getPresentacion()); m.setStock(dto.getStock());
        m.setStockMinimo(dto.getStockMinimo() != null ? dto.getStockMinimo() : 10);
        m.setCentroId(dto.getCentroId()); m.setActivo(true);
        return toDto(repository.save(m));
    }
    public MedicamentoDTO actualizarStock(Long id, Integer cantidad) {
        Medicamento m = repository.findById(id).orElseThrow(() -> new RuntimeException("Medicamento no encontrado: " + id));
        int nuevoStock = m.getStock() + cantidad;
        if (nuevoStock < 0) throw new RuntimeException("Stock insuficiente");
        m.setStock(nuevoStock); return toDto(repository.save(m));
    }
    public void eliminarLogico(Long id) {
        Medicamento m = repository.findById(id).orElseThrow(() -> new RuntimeException("Medicamento no encontrado: " + id));
        m.setActivo(false); repository.save(m);
    }
}
