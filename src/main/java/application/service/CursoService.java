package application.service;

import application.dto.CursoDTO;
import application.model.Curso;
import application.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<CursoDTO> listarTodos() {
        return cursoRepository.findAll().stream()
                .map(c -> new CursoDTO(c.getId(), c.getNome(), c.getDescricao(), c.getCargaHoraria()))
                .collect(Collectors.toList());
    }

    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id).orElseThrow();
    }
}
