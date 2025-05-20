package application.controller;

import application.dto.ModuloDTO;
import application.model.Modulo;
import application.service.ModuloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modulos")
public class ModuloController {

    private final ModuloService moduloService;

    public ModuloController(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    @PostMapping
    public ResponseEntity<Modulo> criar(@RequestBody ModuloDTO dto) {
        return ResponseEntity.ok(moduloService.salvarModulo(dto));
    }

    @GetMapping
    public ResponseEntity<List<Modulo>> listar() {
        return ResponseEntity.ok(moduloService.listarModulos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modulo> buscarPorId(@PathVariable Long id) {
        return moduloService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        moduloService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
