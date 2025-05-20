package application.service;

import application.dto.ModuloDTO;
import application.model.Curso;
import application.model.Modulo;
import application.repository.CursoRepository;
import application.repository.ModuloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuloService {

    private final ModuloRepository moduloRepository;
    private final CursoRepository cursoRepository;

    public ModuloService(ModuloRepository moduloRepository, CursoRepository cursoRepository) {
        this.moduloRepository = moduloRepository;
        this.cursoRepository = cursoRepository;
    }

    public Modulo salvarModulo(ModuloDTO dto) {
        Optional<Curso> cursoOpt = cursoRepository.findById(dto.getCursoId());

        if (cursoOpt.isEmpty()) {
            throw new RuntimeException("Curso não encontrado");
        }

        Modulo modulo = new Modulo();
        modulo.setTitulo(dto.getTitulo());
        modulo.setDescricao(dto.getDescricao());
        modulo.setCurso(cursoOpt.get());

        return moduloRepository.save(modulo);
    }

    public List<Modulo> listarModulos() {
        return moduloRepository.findAll();
    }

    public Optional<Modulo> buscarPorId(Long id) {
        return moduloRepository.findById(id);
    }

    public void deletar(Long id) {
        moduloRepository.deleteById(id);
    }
}
