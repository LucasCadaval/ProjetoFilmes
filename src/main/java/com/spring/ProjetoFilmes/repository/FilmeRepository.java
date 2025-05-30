package com.spring.ProjetoFilmes.repository;

import com.spring.ProjetoFilmes.models.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    List<Filme> findByGenero(String genero);
}