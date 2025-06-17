package utez.edu.mx.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.Models.Almacen;
import utez.edu.mx.Services.AlmacenService;

import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
@RequiredArgsConstructor
public class AlmacenController {

    private final AlmacenService almacenService;

    @PostMapping
    public ResponseEntity<Almacen> crear(@RequestBody Almacen almacen) {
        return new ResponseEntity<>(almacenService.crearAlmacen(almacen), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Almacen> obtenerTodos() {
        return almacenService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Almacen> obtenerPorId(@PathVariable Long id) {
        return almacenService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Almacen> actualizar(@PathVariable Long id, @RequestBody Almacen almacen) {
        return ResponseEntity.ok(almacenService.actualizar(id, almacen));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        almacenService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
