package com.spring.ProjetoFilmes.controller;

import com.spring.ProjetoFilmes.models.Usuario;
import com.spring.ProjetoFilmes.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novo = usuarioRepository.save(usuario);
        return ResponseEntity.ok(novo);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody Usuario loginRequest) {
        String email = loginRequest.getEmail();
        String senha = loginRequest.getSenha();

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuário não encontrado.");
        }

        if (!usuario.getSenha().equals(senha)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Senha incorreta.");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("usuarioId", usuario.getUsuarioId());
        response.put("nomeCompleto", usuario.getNomeCompleto());
        response.put("email", usuario.getEmail());

        return ResponseEntity.ok(response);
    }
}
