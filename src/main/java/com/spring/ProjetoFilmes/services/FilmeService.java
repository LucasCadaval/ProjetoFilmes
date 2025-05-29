package com.spring.ProjetoFilmes.services;

import com.spring.ProjetoFilmes.dto.FilmeDTO;
import com.spring.ProjetoFilmes.models.Filme;
import com.spring.ProjetoFilmes.repository.FilmeRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final AvaliacaoService avaliacaoService;
    private final FavoritoService favoritoService;

    public FilmeDTO criarFilme(FilmeDTO filmeDTO) {
        Filme filme = new Filme();
        filme.setTitulo(filmeDTO.getTitulo());
        filme.setDiretor(filmeDTO.getDiretor());
        filme.setGenero(filmeDTO.getGenero());
        filme.setSinopse(filmeDTO.getSinopse());
        filme.setAnoLancamento(filmeDTO.getAnoLancamento());

        Filme filmeSalvo = filmeRepository.save(filme);
        return toDTO(filmeSalvo);
    }

    public FilmeDTO buscarPorId(Long id, Long usuarioId) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        FilmeDTO dto = toDTO(filme);
        dto.setMediaAvaliacoes(avaliacaoService.calcularMediaAvaliacoes(id));
        dto.setIsFavorito(favoritoService.isFavorito(usuarioId, id));

        return dto;
    }

    private FilmeDTO toDTO(Filme filme) {
        return FilmeDTO.builder()
                .id(filme.getId())
                .titulo(filme.getTitulo())
                .diretor(filme.getDiretor())
                .genero(filme.getGenero())
                .sinopse(filme.getSinopse())
                .anoLancamento(filme.getAnoLancamento())
                .build();
    }
}