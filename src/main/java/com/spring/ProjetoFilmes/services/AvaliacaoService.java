package com.spring.ProjetoFilmes.services;

import com.spring.ProjetoFilmes.dto.AvaliacaoDTO;
import com.spring.ProjetoFilmes.models.Avaliacao;
import com.spring.ProjetoFilmes.models.Filme;
import com.spring.ProjetoFilmes.repository.AvaliacaoRepository;
import com.spring.ProjetoFilmes.repository.FilmeRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final FilmeRepository filmeRepository;

    public AvaliacaoDTO criarAvaliacao(AvaliacaoDTO avaliacaoDTO, Long usuarioId) {
        Filme filme = filmeRepository.findById(avaliacaoDTO.getFilmeId())
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setComentario(avaliacaoDTO.getComentario());
        avaliacao.setFilme(filme);
        avaliacao.setUsuarioId(usuarioId);

        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);
        return toDTO(avaliacaoSalva);
    }

    public Double calcularMediaAvaliacoes(Long filmeId) {
        return avaliacaoRepository.calcularMediaPorFilme(filmeId);
    }

    private AvaliacaoDTO toDTO(Avaliacao avaliacao) {
        return AvaliacaoDTO.builder()
                .id(avaliacao.getId())
                .nota(avaliacao.getNota())
                .comentario(avaliacao.getComentario())
                .filmeId(avaliacao.getFilme().getId())
                .usuarioId(avaliacao.getUsuarioId())
                .build();
    }
}