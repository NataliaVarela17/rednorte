package com.rednorte.urgencias.service;
import com.rednorte.urgencias.dto.UrgenciaDTO;
import com.rednorte.urgencias.model.AtencionUrgencia;
import com.rednorte.urgencias.repository.UrgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class UrgenciaService {
    @Autowired private UrgenciaRepository repository;
    @Autowired private WebClient.Builder webClientBuilder;
    @Value("${services.centros.url:lb://service-centros}") private String centrosUrl;

    private UrgenciaDTO toDto(AtencionUrgencia a) {
        UrgenciaDTO dto = new UrgenciaDTO();
        dto.setId(a.getId()); dto.setPacienteId(a.getPacienteId()); dto.setCentroId(a.getCentroId());
        dto.setFecha(a.getFecha()); dto.setHora(a.getHora()); dto.setPrioridad(a.getPrioridad());
        dto.setMotivoConsulta(a.getMotivoConsulta()); dto.setDiagnostico(a.getDiagnostico());
        dto.setEstado(a.getEstado()); return dto;
    }

    public List<UrgenciaDTO> listar() { return repository.findByActivoTrue().stream().map(this::toDto).collect(Collectors.toList()); }
    public Optional<UrgenciaDTO> porId(Long id) { return repository.findById(id).map(this::toDto); }
    public List<UrgenciaDTO> enEspera() { return repository.findByEstadoAndActivoTrue("EN_ESPERA").stream().map(this::toDto).collect(Collectors.toList()); }

    public UrgenciaDTO registrar(UrgenciaDTO dto) {
        // Verificar centro via WebClient
        try {
            webClientBuilder.build().get().uri(centrosUrl + "/api/centros/" + dto.getCentroId())
                .retrieve().bodyToMono(Map.class).block();
        } catch (Exception e) { throw new RuntimeException("Centro no encontrado: " + dto.getCentroId()); }
        AtencionUrgencia a = new AtencionUrgencia();
        a.setPacienteId(dto.getPacienteId()); a.setCentroId(dto.getCentroId());
        a.setFecha(dto.getFecha()); a.setHora(dto.getHora());
        a.setPrioridad(dto.getPrioridad() != null ? dto.getPrioridad() : "MEDIA");
        a.setMotivoConsulta(dto.getMotivoConsulta()); a.setEstado("EN_ESPERA"); a.setActivo(true);
        return toDto(repository.save(a));
    }

    public UrgenciaDTO atender(Long id, String diagnostico) {
        AtencionUrgencia a = repository.findById(id).orElseThrow(() -> new RuntimeException("Urgencia no encontrada: " + id));
        a.setDiagnostico(diagnostico); a.setEstado("ATENDIDO");
        return toDto(repository.save(a));
    }

    public void cerrar(Long id) {
        AtencionUrgencia a = repository.findById(id).orElseThrow(() -> new RuntimeException("Urgencia no encontrada: " + id));
        a.setEstado("CERRADO"); a.setActivo(false); repository.save(a);
    }
}
