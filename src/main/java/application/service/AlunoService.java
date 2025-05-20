package application.service;

import application.model.Aluno;
import application.repository.AlunoRepository;
import application.security.UsuarioDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService implements UserDetailsService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Aluno aluno = alunoRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Aluno não encontrado"));
        return new UsuarioDetails(aluno);
    }
}
