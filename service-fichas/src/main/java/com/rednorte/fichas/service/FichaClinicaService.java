package com.rednorte.fichas.service;
import com.rednorte.fichas.dto.FichaClinicaDTO;
import com.rednorte.fichas.model.FichaClinica;
import com.rednorte.fichas.repository.FichaClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class FichaClinicaService {
    @Autowired private FichaClinicaRepository repository;
    @Autowired private WebClient.Builder webClientBuilder;
    @Value("${services.pacientes.url:lb://service-pacientes}") private String pacientesUrl;

    private FichaClinicaDTO toDto(FichaClinica f) {
        FichaClinicaDTO dto = new FichaClinicaDTO();
        dto.setId(f.getId()); dto.setPacienteId(f.getPacienteId()); dto.setMedicoId(f.getMedicoId());
        dto.setCitaId(f.getCitaId()); dto.setFecha(f.getFecha());
        dto.setDiagnostico(f.getDiagnostico()); dto.setTratamiento(f.getTratamiento());
        dto.setObservaciones(f.getObservaciones()); return dto;
    }

    public List<FichaClinicaDTO> listar() { return repository.findByActivoTrue().stream().map(this::toDto).collect(Collectors.toList()); }
    public Optional<FichaClinicaDTO> porId(Long id) { return repository.findById(id).map(this::toDto); }
    public List<FichaClinicaDTO> porPaciente(Long pacienteId) { return repository.findByPacienteIdAndActivoTrue(pacienteId).stream().map(this::toDto).collect(Collectors.toList()); }

    public FichaClinicaDTO registrar(FichaClinicaDTO dto) {
        // Verificar que el paciente existe via WebClient
        try {
            webClientBuilder.build().get().uri(pacientesUrl + "/api/pacientes/" + dto.getPacienteId())
                .retrieve().bodyToMono(Map.class).block();
        } catch (Exception e) { throw new RuntimeException("Paciente no encontrado: " + dto.getPacienteId()); }
        FichaClinica f = new FichaClinica();
        f.setPacienteId(dto.getPacienteId()); f.setMedicoId(dto.getMedicoId());
        f.setCitaId(dto.getCitaId()); f.setFecha(dto.getFecha());
        f.setDiagnostico(dto.getDiagnostico()); f.setTratamiento(dto.getTratamiento());
        f.setObservaciones(dto.getObservaciones()); f.setActivo(true);
        return toDto(repository.save(f));
    }

    public void eliminarLogico(Long id) {
        FichaClinica f = repository.findById(id).orElseThrow(() -> new RuntimeException("Ficha no encontrada: " + id));
        f.setActivo(false); repository.save(f);
    }
}
