package com.rednorte.pacientes.service;
import com.rednorte.pacientes.dto.PacienteDTO;
import com.rednorte.pacientes.model.Paciente;
import com.rednorte.pacientes.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PacienteService {
    @Autowired private PacienteRepository repository;

    private PacienteDTO toDto(Paciente p) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(p.getId()); dto.setRut(p.getRut());
        dto.setNombre(p.getNombre()); dto.setApellido(p.getApellido());
        dto.setTelefono(p.getTelefono()); dto.setEmail(p.getEmail());
        dto.setDireccion(p.getDireccion()); dto.setFechaNacimiento(p.getFechaNacimiento());
        return dto;
    }

    public List<PacienteDTO> listarActivos() {
        return repository.findByActivoTrue().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<PacienteDTO> buscarPorId(Long id) {
        return repository.findById(id).map(this::toDto);
    }

    public Optional<PacienteDTO> buscarPorRut(String rut) {
        return repository.findByRut(rut).map(this::toDto);
    }

    public PacienteDTO registrar(PacienteDTO dto) {
        if (repository.findByRut(dto.getRut()).isPresent()) {
            throw new RuntimeException("Ya existe un paciente con RUT: " + dto.getRut());
        }
        Paciente p = new Paciente();
        p.setRut(dto.getRut()); p.setNombre(dto.getNombre()); p.setApellido(dto.getApellido());
        p.setTelefono(dto.getTelefono()); p.setEmail(dto.getEmail());
        p.setDireccion(dto.getDireccion()); p.setFechaNacimiento(dto.getFechaNacimiento());
        p.setActivo(true);
        return toDto(repository.save(p));
    }

    public PacienteDTO actualizar(Long id, PacienteDTO dto) {
        Paciente p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado: " + id));
        p.setNombre(dto.getNombre()); p.setApellido(dto.getApellido());
        p.setTelefono(dto.getTelefono()); p.setEmail(dto.getEmail());
        p.setDireccion(dto.getDireccion()); p.setFechaNacimiento(dto.getFechaNacimiento());
        return toDto(repository.save(p));
    }

    public void eliminarLogico(Long id) {
        Paciente p = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado: " + id));
        p.setActivo(false);
        repository.save(p);
    }
}
