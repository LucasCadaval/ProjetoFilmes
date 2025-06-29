package com.spring.ProjetoFilmes.repository;

import com.spring.ProjetoFilmes.models.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findByUsuarioId(Long usuarioId);
    Optional<Favorito> findByUsuarioIdAndFilmeId(Long usuarioId, Long filmeId);

}
