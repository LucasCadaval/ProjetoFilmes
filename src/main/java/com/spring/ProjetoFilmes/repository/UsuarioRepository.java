package com.spring.ProjetoFilmes.repository;

import com.spring.ProjetoFilmes.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
}
