package com.spring.ProjetoFilmes.services;

import com.spring.ProjetoFilmes.dto.AvaliacaoDTO;
import com.spring.ProjetoFilmes.models.Avaliacao;
import com.spring.ProjetoFilmes.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoDTO criarAvaliacao(AvaliacaoDTO avaliacaoDTO, Long usuarioId) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setComentario(avaliacaoDTO.getComentario());
        avaliacao.setFilmeId(avaliacaoDTO.getFilmeId());
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
                .filmeId(avaliacao.getFilmeId())
                .usuarioId(avaliacao.getUsuarioId())
                .build();
    }
}