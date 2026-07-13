package com.rednorte.medicos.service;
import com.rednorte.medicos.dto.MedicoDTO;
import com.rednorte.medicos.model.Medico;
import com.rednorte.medicos.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class MedicoService {
    @Autowired private MedicoRepository repository;

    private MedicoDTO toDto(Medico m) {
        MedicoDTO dto = new MedicoDTO();
        dto.setId(m.getId()); dto.setRut(m.getRut()); dto.setNombre(m.getNombre());
        dto.setApellido(m.getApellido()); dto.setEspecialidad(m.getEspecialidad());
        dto.setCentroId(m.getCentroId()); dto.setTelefono(m.getTelefono()); dto.setEmail(m.getEmail());
        return dto;
    }

    public List<MedicoDTO> listarActivos() {
        return repository.findByActivoTrue().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<MedicoDTO> buscarPorId(Long id) { return repository.findById(id).map(this::toDto); }

    public List<MedicoDTO> porEspecialidad(String especialidad) {
        return repository.findByEspecialidadAndActivoTrue(especialidad).stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<MedicoDTO> porCentro(Long centroId) {
        return repository.findByCentroIdAndActivoTrue(centroId).stream().map(this::toDto).collect(Collectors.toList());
    }

    public MedicoDTO registrar(MedicoDTO dto) {
        if (repository.findByRut(dto.getRut()).isPresent())
            throw new RuntimeException("Ya existe un médico con RUT: " + dto.getRut());
        Medico m = new Medico();
        m.setRut(dto.getRut()); m.setNombre(dto.getNombre()); m.setApellido(dto.getApellido());
        m.setEspecialidad(dto.getEspecialidad()); m.setCentroId(dto.getCentroId());
        m.setTelefono(dto.getTelefono()); m.setEmail(dto.getEmail()); m.setActivo(true);
        return toDto(repository.save(m));
    }

    public MedicoDTO actualizar(Long id, MedicoDTO dto) {
        Medico m = repository.findById(id).orElseThrow(() -> new RuntimeException("Médico no encontrado: " + id));
        m.setNombre(dto.getNombre()); m.setApellido(dto.getApellido());
        m.setEspecialidad(dto.getEspecialidad()); m.setCentroId(dto.getCentroId());
        m.setTelefono(dto.getTelefono()); m.setEmail(dto.getEmail());
        return toDto(repository.save(m));
    }

    public void desactivar(Long id) {
        Medico m = repository.findById(id).orElseThrow(() -> new RuntimeException("Médico no encontrado: " + id));
        m.setActivo(false); repository.save(m);
    }
}
