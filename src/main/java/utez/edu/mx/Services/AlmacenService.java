package utez.edu.mx.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.DTO.AlmacenDTO;
import utez.edu.mx.Models.Almacen;
import utez.edu.mx.Models.Cede;
import utez.edu.mx.Repositories.AlmacenRepository;
import utez.edu.mx.Repositories.CedeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlmacenService {

    private final AlmacenRepository almacenRepository;
    private final CedeRepository cedeRepository;

    // Convertir entidad a DTO
    private AlmacenDTO toDTO(Almacen almacen) {
        AlmacenDTO dto = new AlmacenDTO();
        dto.setId(almacen.getId());
        dto.setClaveAlmacen(almacen.getClaveAlmacen());
        dto.setCedeId(almacen.getCede().getId());
        dto.setFechaRegistro(almacen.getFechaRegistro());
        dto.setPrecioVenta(almacen.getPrecioVenta());
        dto.setPrecioRenta(almacen.getPrecioRenta());
        dto.setTamaño(almacen.getTamaño());
        return dto;
    }

    // Convertir DTO a entidad (solo para crear/actualizar)
    private Almacen toEntity(AlmacenDTO dto) {
        Almacen almacen = new Almacen();
        almacen.setPrecioVenta(dto.getPrecioVenta());
        almacen.setPrecioRenta(dto.getPrecioRenta());
        almacen.setTamaño(dto.getTamaño());

        if(dto.getCedeId() != null) {
            Cede cede = cedeRepository.findById(dto.getCedeId())
                    .orElseThrow(() -> new RuntimeException("Cede no encontrada con id: " + dto.getCedeId()));
            almacen.setCede(cede);
        } else {
            throw new RuntimeException("Cede es obligatoria");
        }
        return almacen;
    }

    public AlmacenDTO crearAlmacen(AlmacenDTO dto) {
        Almacen almacen = toEntity(dto);
        Almacen saved = almacenRepository.save(almacen);

        String claveAlmacen = saved.getCede().getClaveCede() + "-A" + saved.getId();
        saved.setClaveAlmacen(claveAlmacen);
        almacenRepository.save(saved);

        return toDTO(saved);
    }

    public List<AlmacenDTO> obtenerTodos() {
        return almacenRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AlmacenDTO> obtenerPorId(Long id) {
        return almacenRepository.findById(id).map(this::toDTO);
    }

    public AlmacenDTO actualizar(Long id, AlmacenDTO dto) {
        return almacenRepository.findById(id).map(almacen -> {
            almacen.setPrecioVenta(dto.getPrecioVenta());
            almacen.setPrecioRenta(dto.getPrecioRenta());
            almacen.setTamaño(dto.getTamaño());

            if(dto.getCedeId() != null) {
                Cede cede = cedeRepository.findById(dto.getCedeId())
                        .orElseThrow(() -> new RuntimeException("Cede no encontrada con id: " + dto.getCedeId()));
                almacen.setCede(cede);
            } else {
                throw new RuntimeException("Cede es obligatoria");
            }

            Almacen updated = almacenRepository.save(almacen);
            return toDTO(updated);
        }).orElseThrow(() -> new RuntimeException("Almacen no encontrado"));
    }

    public void eliminar(Long id) {
        almacenRepository.deleteById(id);
    }
}
