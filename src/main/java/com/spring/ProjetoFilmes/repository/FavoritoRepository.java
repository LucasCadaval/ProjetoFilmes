package com.spring.ProjetoFilmes.repository;

import com.spring.ProjetoFilmes.models.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findByUsuarioId(Long usuarioId);

    boolean existsByUsuarioIdAndFilmeId(Long usuarioId, Long filmeId);
    void deleteByUsuarioIdAndFilmeId(Long usuarioId, Long filmeId);
}