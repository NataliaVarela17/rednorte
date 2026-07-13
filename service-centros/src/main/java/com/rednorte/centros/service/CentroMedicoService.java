package com.rednorte.centros.service;
import com.rednorte.centros.dto.CentroDTO;
import com.rednorte.centros.model.CentroMedico;
import com.rednorte.centros.repository.CentroMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CentroMedicoService {
    @Autowired private CentroMedicoRepository repository;

    private CentroDTO toDto(CentroMedico c) {
        CentroDTO dto = new CentroDTO();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setDireccion(c.getDireccion());
        dto.setTelefono(c.getTelefono());
        dto.setComuna(c.getComuna());
        dto.setTipo(c.getTipo());
        return dto;
    }

    private CentroMedico fromDto(CentroDTO dto) {
        CentroMedico c = new CentroMedico();
        c.setNombre(dto.getNombre());
        c.setDireccion(dto.getDireccion());
        c.setTelefono(dto.getTelefono());
        c.setComuna(dto.getComuna());
        c.setTipo(dto.getTipo() != null ? dto.getTipo() : "HOSPITAL");
        c.setActivo(true);
        return c;
    }

    public List<CentroDTO> obtenerTodosActivos() {
        return repository.findByActivoTrue().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<CentroDTO> obtenerPorId(Long id) {
        return repository.findById(id).map(this::toDto);
    }

    public CentroDTO registrar(CentroDTO dto) {
        return toDto(repository.save(fromDto(dto)));
    }

    public CentroDTO actualizar(Long id, CentroDTO dto) {
        CentroMedico c = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Centro no encontrado: " + id));
        c.setNombre(dto.getNombre());
        c.setDireccion(dto.getDireccion());
        c.setTelefono(dto.getTelefono());
        c.setComuna(dto.getComuna());
        if (dto.getTipo() != null) c.setTipo(dto.getTipo());
        return toDto(repository.save(c));
    }

    public void desactivar(Long id) {
        CentroMedico c = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Centro no encontrado: " + id));
        c.setActivo(false);
        repository.save(c);
    }
}
