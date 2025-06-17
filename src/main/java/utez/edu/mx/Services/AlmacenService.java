package utez.edu.mx.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.Models.Almacen;
import utez.edu.mx.Repositories.AlmacenRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlmacenService {

    private final AlmacenRepository almacenRepository;

    public Almacen crearAlmacen(Almacen almacen) {
        // Primero guardar para obtener ID
        Almacen saved = almacenRepository.save(almacen);

        // Generar clave almacen
        String claveAlmacen = saved.getCede().getClaveCede() + "-A" + saved.getId();
        saved.setClaveAlmacen(claveAlmacen);

        return almacenRepository.save(saved);
    }

    public List<Almacen> obtenerTodos() {
        return almacenRepository.findAll();
    }

    public Optional<Almacen> obtenerPorId(Long id) {
        return almacenRepository.findById(id);
    }

    public Almacen actualizar(Long id, Almacen almacen) {
        return almacenRepository.findById(id).map(a -> {
            a.setPrecioVenta(almacen.getPrecioVenta());
            a.setPrecioRenta(almacen.getPrecioRenta());
            a.setTamaño(almacen.getTamaño());
            a.setCede(almacen.getCede());
            return almacenRepository.save(a);
        }).orElseThrow(() -> new RuntimeException("Almacen no encontrado"));
    }

    public void eliminar(Long id) {
        almacenRepository.deleteById(id);
    }
}
