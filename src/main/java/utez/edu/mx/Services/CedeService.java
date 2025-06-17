package utez.edu.mx.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.DTO.CedeDto;
import utez.edu.mx.Models.Cede;
import utez.edu.mx.Repositories.CedeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CedeService {

    private final CedeRepository cedeRepository;

    public CedeDto crear(CedeDto dto) {
        Cede cede = new Cede();
        cede.setEstado(dto.getEstado());
        cede.setMunicipio(dto.getMunicipio());

        Cede saved = cedeRepository.save(cede);
        String clave = "C" + saved.getId() + "-" + java.time.LocalDate.now().toString().replace("-", "") + "-" + (int)(Math.random() * 10000);
        saved.setClaveCede(clave);
        cedeRepository.save(saved);

        dto.setId(saved.getId());
        dto.setClaveCede(clave);
        return dto;
    }

    public List<CedeDto> obtenerTodos() {
        return cedeRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public CedeDto obtenerPorId(Long id) {
        Cede cede = cedeRepository.findById(id).orElseThrow(() -> new RuntimeException("Cede no encontrada"));
        return toDto(cede);
    }

    public CedeDto actualizar(Long id, CedeDto dto) {
        Cede cede = cedeRepository.findById(id).orElseThrow(() -> new RuntimeException("Cede no encontrada"));
        cede.setEstado(dto.getEstado());
        cede.setMunicipio(dto.getMunicipio());
        cedeRepository.save(cede);
        return toDto(cede);
    }

    public void eliminar(Long id) {
        cedeRepository.deleteById(id);
    }

    private CedeDto toDto(Cede cede) {
        CedeDto dto = new CedeDto();
        dto.setId(cede.getId());
        dto.setClaveCede(cede.getClaveCede());
        dto.setEstado(cede.getEstado());
        dto.setMunicipio(cede.getMunicipio());
        return dto;
    }
}
