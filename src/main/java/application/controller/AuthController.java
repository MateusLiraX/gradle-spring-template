package application.controller;

import application.model.Aluno;
import application.model.Usuario; // ✅ Ajuste aqui o caminho correto da classe Usuario
import application.repository.UsuarioRepository;
import application.security.JwtUtil;
import application.security.UsuarioDetails;
import application.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Endpoint público para criar um novo aluno com usuário
    @PostMapping("/usuario")
    public ResponseEntity<?> registrar(@RequestBody @Valid AlunoDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setUsuario(usuario);

        alunoService.salvar(aluno);
        return ResponseEntity.ok("Aluno registrado com sucesso!");
    }

    // Endpoint público para autenticação e geração de token
    @PostMapping("/auth")
    public ResponseEntity<?> autenticar(@RequestBody @Valid AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha()));

        UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(usuarioDetails.getUsername());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    // DTO para registro
    public static class AlunoDTO {
        private String nome;
        private String email;
        private String senha;

        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }

    // DTO para login
    public static class AuthRequest {
        private String email;
        private String senha;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }

    // Resposta com o token
    public static class AuthResponse {
        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }
}
