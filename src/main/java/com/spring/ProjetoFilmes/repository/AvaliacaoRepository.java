package com.spring.ProjetoFilmes.repository;

import com.spring.ProjetoFilmes.models.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao> findByFilmeId(Long filmeId);

    @Query("SELECT AVG(a.nota) FROM Avaliacao a WHERE a.filmeId = :filmeId")
    Double calcularMediaPorFilme(Long filmeId);

    List<Avaliacao> findByUsuarioId(Long usuarioId);

}
