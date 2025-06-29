package com.spring.ProjetoFilmes.services;

import com.spring.ProjetoFilmes.dto.AvaliacaoDTO;
import com.spring.ProjetoFilmes.models.Avaliacao;
import com.spring.ProjetoFilmes.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public Avaliacao cadastrar(AvaliacaoDTO dto) {
        Avaliacao avaliacao = Avaliacao.builder()
                .usuarioId(dto.getUsuarioId())
                .filmeId(dto.getFilmeId())
                .nota(dto.getNota())
                .comentario(dto.getComentario())
                .build();

        return avaliacaoRepository.save(avaliacao);
    }


    public List<Avaliacao> listarPorFilme(Long filmeId) {
        return avaliacaoRepository.findByFilmeId(filmeId);
    }

    public Double calcularMediaAvaliacoes(Long filmeId) {
        return avaliacaoRepository.calcularMediaPorFilme(filmeId);
    }

    public AvaliacaoDTO toDTO(Avaliacao avaliacao) {
        return AvaliacaoDTO.builder()
                .id(avaliacao.getId())
                .nota(avaliacao.getNota())
                .comentario(avaliacao.getComentario())
                .filmeId(avaliacao.getFilmeId())
                .build();
    }
}
