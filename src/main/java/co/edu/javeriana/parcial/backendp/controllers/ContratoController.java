package co.edu.javeriana.parcial.backendp.controllers;

import co.edu.javeriana.parcial.backendp.dto.ContratoDTO;
import co.edu.javeriana.parcial.backendp.service.ContratoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    private final ContratoService contratoService;

    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    // Obtener todos los contratos
    @GetMapping
    public List<ContratoDTO> getAllContratos() {
        return contratoService.getAll();
    }

    // Obtener un contrato por ID
    @GetMapping("/{id}")
    public ResponseEntity<ContratoDTO> getContratoById(@PathVariable Long id) {
        ContratoDTO contrato = contratoService.getById(id);
        return ResponseEntity.ok(contrato);
    }

    // Crear un nuevo contrato
    @PostMapping
    public ResponseEntity<ContratoDTO> createContrato(@RequestBody ContratoDTO contratoDTO) {
        ContratoDTO newContrato = contratoService.create(contratoDTO);
        return ResponseEntity.ok(newContrato);
    }

    // Actualizar un contrato existente
    @PutMapping("/{id}")
    public ResponseEntity<ContratoDTO> updateContrato(@PathVariable Long id, @RequestBody ContratoDTO contratoDTO) {
        ContratoDTO updatedContrato = contratoService.update(id, contratoDTO);
        return ResponseEntity.ok(updatedContrato);
    }

    // Eliminar un contrato (eliminación lógica o física)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrato(@PathVariable Long id) {
        contratoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
