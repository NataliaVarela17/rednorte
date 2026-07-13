package com.rednorte.laboratorio.service;
import com.rednorte.laboratorio.dto.ExamenDTO;
import com.rednorte.laboratorio.model.ExamenLaboratorio;
import com.rednorte.laboratorio.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class ExamenService {
    @Autowired private ExamenRepository repository;
    private ExamenDTO toDto(ExamenLaboratorio e) {
        ExamenDTO dto = new ExamenDTO();
        dto.setId(e.getId()); dto.setPacienteId(e.getPacienteId()); dto.setMedicoId(e.getMedicoId());
        dto.setTipoExamen(e.getTipoExamen()); dto.setFechaSolicitud(e.getFechaSolicitud());
        dto.setFechaResultado(e.getFechaResultado()); dto.setResultado(e.getResultado());
        dto.setEstado(e.getEstado()); return dto;
    }
    public List<ExamenDTO> listar() { return repository.findByActivoTrue().stream().map(this::toDto).collect(Collectors.toList()); }
    public Optional<ExamenDTO> porId(Long id) { return repository.findById(id).map(this::toDto); }
    public List<ExamenDTO> porPaciente(Long id) { return repository.findByPacienteIdAndActivoTrue(id).stream().map(this::toDto).collect(Collectors.toList()); }
    public ExamenDTO solicitar(ExamenDTO dto) {
        ExamenLaboratorio e = new ExamenLaboratorio();
        e.setPacienteId(dto.getPacienteId()); e.setMedicoId(dto.getMedicoId());
        e.setTipoExamen(dto.getTipoExamen()); e.setFechaSolicitud(dto.getFechaSolicitud());
        e.setEstado("SOLICITADO"); e.setActivo(true);
        return toDto(repository.save(e));
    }
    public ExamenDTO ingresarResultado(Long id, String resultado, String fecha) {
        ExamenLaboratorio e = repository.findById(id).orElseThrow(() -> new RuntimeException("Examen no encontrado: " + id));
        e.setResultado(resultado); e.setFechaResultado(fecha); e.setEstado("COMPLETADO");
        return toDto(repository.save(e));
    }
    public void eliminarLogico(Long id) {
        ExamenLaboratorio e = repository.findById(id).orElseThrow(() -> new RuntimeException("Examen no encontrado: " + id));
        e.setActivo(false); repository.save(e);
    }
}
