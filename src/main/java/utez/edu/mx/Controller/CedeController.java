package utez.edu.mx.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.DTO.CedeDto;
import utez.edu.mx.Services.CedeService;

import java.util.List;

@RestController
@RequestMapping("/api/cedes")
@RequiredArgsConstructor
public class CedeController {

    private final CedeService cedeService;

    @PostMapping
    public ResponseEntity<CedeDto> crear(@RequestBody CedeDto dto) {
        return ResponseEntity.ok(cedeService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<CedeDto>> obtenerTodos() {
        return ResponseEntity.ok(cedeService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CedeDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cedeService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CedeDto> actualizar(@PathVariable Long id, @RequestBody CedeDto dto) {
        return ResponseEntity.ok(cedeService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cedeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
