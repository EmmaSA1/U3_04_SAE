package utez.edu.mx.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.DTO.AlmacenDTO;
import utez.edu.mx.Services.AlmacenService;

import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
@RequiredArgsConstructor
public class AlmacenController {

    private final AlmacenService almacenService;

    @PostMapping
    public ResponseEntity<AlmacenDTO> crear(@RequestBody AlmacenDTO almacenDTO) {
        return new ResponseEntity<>(almacenService.crearAlmacen(almacenDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AlmacenDTO>> obtenerTodos() {
        return ResponseEntity.ok(almacenService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlmacenDTO> obtenerPorId(@PathVariable Long id) {
        return almacenService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlmacenDTO> actualizar(@PathVariable Long id, @RequestBody AlmacenDTO almacenDTO) {
        return ResponseEntity.ok(almacenService.actualizar(id, almacenDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        almacenService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
