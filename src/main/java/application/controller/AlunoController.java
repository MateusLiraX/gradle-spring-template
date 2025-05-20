package application.controller;

import application.dto.AlunoDTO;
import application.model.Aluno;
import application.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

   @GetMapping
    public List<AlunoDTO> listar() {
    List<Aluno> alunos = alunoService.listarTodos();
        return alunos.stream()
                 .map(aluno -> new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail()))
                 .toList();
}


    @PostMapping
    public Aluno salvar(@RequestBody Aluno aluno) {
        return alunoService.salvar(aluno);
    }
}
