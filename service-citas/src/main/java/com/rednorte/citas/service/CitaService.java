package com.rednorte.citas.service;

import com.rednorte.citas.dto.CitaDTO;
import com.rednorte.citas.model.Cita;
import com.rednorte.citas.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaService {

    @Autowired private CitaRepository repository;
    @Autowired private WebClient.Builder webClientBuilder;

    @Value("${services.pacientes.url:lb://service-pacientes}")
    private String pacientesUrl;

    @Value("${services.medicos.url:lb://service-medicos}")
    private String medicosUrl;

    @Value("${services.centros.url:lb://service-centros}")
    private String centrosUrl;

    private CitaDTO toDto(Cita c) {
        CitaDTO dto = new CitaDTO();
        dto.setId(c.getId());
        dto.setPacienteId(c.getPacienteId());
        dto.setMedicoId(c.getMedicoId());
        dto.setCentroId(c.getCentroId());
        dto.setFecha(c.getFecha());
        dto.setHora(c.getHora());
        dto.setEstado(c.getEstado());
        dto.setObservaciones(c.getObservaciones());
        return dto;
    }

    private CitaDTO toDtoEnriquecido(Cita c) {
        CitaDTO dto = toDto(c);

        try {
            Map<?, ?> paciente = webClientBuilder.build()
                .get().uri(pacientesUrl + "/api/pacientes/" + c.getPacienteId())
                .retrieve().bodyToMono(Map.class).block();
            if (paciente != null)
                dto.setNombrePaciente(paciente.get("nombre") + " " + paciente.get("apellido"));
        } catch (Exception e) {
            dto.setNombrePaciente("No disponible");
        }

        try {
            Map<?, ?> medico = webClientBuilder.build()
                .get().uri(medicosUrl + "/api/medicos/" + c.getMedicoId())
                .retrieve().bodyToMono(Map.class).block();
            if (medico != null)
                dto.setNombreMedico("Dr. " + medico.get("nombre") + " " + medico.get("apellido"));
        } catch (Exception e) {
            dto.setNombreMedico("No disponible");
        }

        try {
            Map<?, ?> centro = webClientBuilder.build()
                .get().uri(centrosUrl + "/api/centros/" + c.getCentroId())
                .retrieve().bodyToMono(Map.class).block();
            if (centro != null)
                dto.setNombreCentro((String) centro.get("nombre"));
        } catch (Exception e) {
            dto.setNombreCentro("No disponible");
        }

        return dto;
    }

    public List<CitaDTO> listarActivas() {
        return repository.findByActivoTrue().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<CitaDTO> buscarPorId(Long id) {
        return repository.findById(id).map(this::toDtoEnriquecido);
    }

    public List<CitaDTO> porPaciente(Long pacienteId) {
        return repository.findByPacienteIdAndActivoTrue(pacienteId).stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<CitaDTO> porMedico(Long medicoId) {
        return repository.findByMedicoIdAndActivoTrue(medicoId).stream().map(this::toDto).collect(Collectors.toList());
    }

    public CitaDTO agendar(CitaDTO dto) {
        Cita c = new Cita();
        c.setPacienteId(dto.getPacienteId());
        c.setMedicoId(dto.getMedicoId());
        c.setCentroId(dto.getCentroId());
        c.setFecha(dto.getFecha());
        c.setHora(dto.getHora());
        c.setObservaciones(dto.getObservaciones());
        c.setEstado("PENDIENTE");
        c.setActivo(true);
        return toDto(repository.save(c));
    }

    public CitaDTO cambiarEstado(Long id, String estado) {
        Cita c = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cita no encontrada: " + id));
        c.setEstado(estado);
        return toDto(repository.save(c));
    }

    public void cancelar(Long id) {
        Cita c = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cita no encontrada: " + id));
        c.setEstado("CANCELADA");
        c.setActivo(false);
        repository.save(c);
    }
}
