package application.controller;

import application.dto.CursoDTO;
import application.model.Curso;
import application.service.CursoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<CursoDTO> listar() {
        return cursoService.listarTodos();
    }

    @PostMapping
    public Curso salvar(@RequestBody Curso curso) {
        return cursoService.salvar(curso);
    }
}
