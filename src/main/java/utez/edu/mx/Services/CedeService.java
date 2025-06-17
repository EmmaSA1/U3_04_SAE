package utez.edu.mx.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.Models.Cede;
import utez.edu.mx.Repositories.CedeRepository;

import java.util.List;
import java.util.Optional;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CedeService {

    private final CedeRepository cedeRepository;

    public Cede crearCede(Cede cede) {
        // Guardar para que se genere ID
        Cede saved = cedeRepository.save(cede);

        // Generar clave con el ID generado
        String fecha = new SimpleDateFormat("ddMMyyyy").format(new Date());
        int aleatorio = (int)(Math.random() * 9000) + 1000;
        saved.setClaveCede("C" + saved.getId() + "-" + fecha + "-" + aleatorio);

        return cedeRepository.save(saved);
    }

    public List<Cede> obtenerTodas() {
        return cedeRepository.findAll();
    }

    public Optional<Cede> obtenerPorId(Long id) {
        return cedeRepository.findById(id);
    }

    public Cede actualizar(Long id, Cede cede) {
        return cedeRepository.findById(id).map(c -> {
            c.setEstado(cede.getEstado());
            c.setMunicipio(cede.getMunicipio());
            return cedeRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Cede no encontrada"));
    }

    public void eliminar(Long id) {
        cedeRepository.deleteById(id);
    }
}
