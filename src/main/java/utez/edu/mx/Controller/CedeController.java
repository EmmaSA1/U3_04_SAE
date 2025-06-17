package utez.edu.mx.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.Models.Cede;
import utez.edu.mx.Services.CedeService;

import java.util.List;

@RestController
@RequestMapping("/api/cedes")
@RequiredArgsConstructor
public class CedeController {

    private final CedeService cedeService;

    @PostMapping
    public ResponseEntity<Cede> crear(@RequestBody Cede cede) {
        return new ResponseEntity<>(cedeService.crearCede(cede), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Cede> obtenerTodas() {
        return cedeService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cede> obtenerPorId(@PathVariable Long id) {
        return cedeService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cede> actualizar(@PathVariable Long id, @RequestBody Cede cede) {
        return ResponseEntity.ok(cedeService.actualizar(id, cede));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cedeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
