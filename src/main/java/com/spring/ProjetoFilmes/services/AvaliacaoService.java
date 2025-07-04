package com.spring.ProjetoFilmes.services;

import com.spring.ProjetoFilmes.dto.AvaliacaoDTO;
import com.spring.ProjetoFilmes.models.Avaliacao;
import com.spring.ProjetoFilmes.repository.AvaliacaoRepository;
import com.spring.ProjetoFilmes.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    private final UsuarioRepository usuarioRepository;


//    public Avaliacao cadastrar(AvaliacaoDTO dto) {
//        Avaliacao avaliacao = Avaliacao.builder()
//                .usuarioId(dto.getUsuarioId())
//                .filmeId(dto.getFilmeId())
//                .nota(dto.getNota())
//                .comentario(dto.getComentario())
//                .build();
//
//        return avaliacaoRepository.save(avaliacao);
//    }


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

    public List<Avaliacao> listarPorUsuario(Long usuarioId) {
        return avaliacaoRepository.findByUsuarioId(usuarioId);
    }

    public void remover(Long id) {
        avaliacaoRepository.deleteById(id);
    }

    public Avaliacao cadastrar(AvaliacaoDTO dto) {
        if (!usuarioRepository.existsById(dto.getUsuarioId())) {
            throw new RuntimeException("Usuário não existe com ID: " + dto.getUsuarioId());
        }

        Avaliacao avaliacao = Avaliacao.builder()
                .usuarioId(dto.getUsuarioId())
                .filmeId(dto.getFilmeId())
                .nota(dto.getNota())
                .comentario(dto.getComentario())
                .build();

        return avaliacaoRepository.save(avaliacao);
    }


}
