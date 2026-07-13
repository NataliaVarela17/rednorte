package com.rednorte.recursos.service;
import com.rednorte.recursos.dto.*;
import com.rednorte.recursos.model.*;
import com.rednorte.recursos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecursoService {
    @Autowired private SalaRepository salaRepo;
    @Autowired private EquipamientoRepository equipRepo;

    // ─── Mapper Sala ───────────────────────────────────────────
    private SalaDTO toSalaDto(Sala s) {
        SalaDTO d = new SalaDTO();
        d.setId(s.getId()); d.setCodigo(s.getCodigo());
        d.setTipo(s.getTipo()); d.setCentroId(s.getCentroId());
        d.setDisponible(s.isDisponible()); return d;
    }

    // ─── Mapper Equipamiento ───────────────────────────────────
    private EquipamientoDTO toEquipoDto(Equipamiento e) {
        EquipamientoDTO d = new EquipamientoDTO();
        d.setId(e.getId()); d.setNombre(e.getNombre());
        d.setSalaId(e.getSalaId()); d.setEstado(e.getEstado()); return d;
    }

    // ─── SALAS ────────────────────────────────────────────────
    public List<SalaDTO> obtenerSalasDisponibles() {
        return salaRepo.findByDisponibleTrueAndActivoTrue().stream().map(this::toSalaDto).collect(Collectors.toList());
    }

    public List<SalaDTO> obtenerTodasSalas() {
        return salaRepo.findByActivoTrue().stream().map(this::toSalaDto).collect(Collectors.toList());
    }

    public List<SalaDTO> obtenerSalasPorCentro(Long centroId) {
        return salaRepo.findByCentroIdAndActivoTrue(centroId).stream().map(this::toSalaDto).collect(Collectors.toList());
    }

    public Optional<SalaDTO> obtenerSalaPorId(Long id) {
        return salaRepo.findById(id).filter(Sala::isActivo).map(this::toSalaDto);
    }

    public SalaDTO crearSala(SalaDTO dto) {
        if (salaRepo.existsByCodigo(dto.getCodigo()))
            throw new RuntimeException("Ya existe una sala con código: " + dto.getCodigo());
        Sala s = new Sala();
        s.setCodigo(dto.getCodigo()); s.setTipo(dto.getTipo());
        s.setCentroId(dto.getCentroId()); s.setDisponible(true); s.setActivo(true);
        return toSalaDto(salaRepo.save(s));
    }

    public SalaDTO actualizarSala(Long id, SalaDTO dto) {
        Sala s = salaRepo.findById(id).orElseThrow(() -> new RuntimeException("Sala no encontrada: " + id));
        s.setTipo(dto.getTipo()); s.setCentroId(dto.getCentroId());
        s.setDisponible(dto.isDisponible());
        return toSalaDto(salaRepo.save(s));
    }

    public void desactivarSala(Long id) {
        Sala s = salaRepo.findById(id).orElseThrow(() -> new RuntimeException("Sala no encontrada: " + id));
        s.setActivo(false); salaRepo.save(s);
    }

    // ─── EQUIPAMIENTO ─────────────────────────────────────────
    public List<EquipamientoDTO> obtenerEquipamiento() {
        return equipRepo.findByActivoTrue().stream().map(this::toEquipoDto).collect(Collectors.toList());
    }

    public List<EquipamientoDTO> obtenerEquipamientoPorSala(Long salaId) {
        return equipRepo.findBySalaIdAndActivoTrue(salaId).stream().map(this::toEquipoDto).collect(Collectors.toList());
    }

    public Optional<EquipamientoDTO> obtenerEquipoPorId(Long id) {
        return equipRepo.findById(id).filter(Equipamiento::isActivo).map(this::toEquipoDto);
    }

    public EquipamientoDTO crearEquipamiento(EquipamientoDTO dto) {
        Equipamiento e = new Equipamiento();
        e.setNombre(dto.getNombre()); e.setSalaId(dto.getSalaId());
        e.setEstado("OPERATIVO"); e.setActivo(true);
        return toEquipoDto(equipRepo.save(e));
    }

    public EquipamientoDTO actualizarEstadoEquipo(Long id, String estado) {
        Equipamiento e = equipRepo.findById(id).orElseThrow(() -> new RuntimeException("Equipamiento no encontrado: " + id));
        e.setEstado(estado);
        return toEquipoDto(equipRepo.save(e));
    }

    public void desactivarEquipo(Long id) {
        Equipamiento e = equipRepo.findById(id).orElseThrow(() -> new RuntimeException("Equipamiento no encontrado: " + id));
        e.setActivo(false); equipRepo.save(e);
    }
}
