package com.spring.ProjetoFilmes.services;

import com.spring.ProjetoFilmes.dto.FavoritoDTO;
import com.spring.ProjetoFilmes.models.Favorito;
import com.spring.ProjetoFilmes.models.Filme;
import com.spring.ProjetoFilmes.repository.FavoritoRepository;
import com.spring.ProjetoFilmes.repository.FilmeRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final FilmeRepository filmeRepository;

    public FavoritoDTO marcarFavorito(Long usuarioId, Long filmeId) {
        if (favoritoRepository.existsByUsuarioIdAndFilmeId(usuarioId, filmeId)) {
            throw new RuntimeException("Filme já está nos favoritos");
        }

        Filme filme = filmeRepository.findById(filmeId)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        Favorito favorito = new Favorito();
        favorito.setUsuarioId(usuarioId);
        favorito.setFilme(filme);

        Favorito favoritoSalvo = favoritoRepository.save(favorito);
        return toDTO(favoritoSalvo);
    }

    public boolean isFavorito(Long usuarioId, Long filmeId) {
        return favoritoRepository.existsByUsuarioIdAndFilmeId(usuarioId, filmeId);
    }

    private FavoritoDTO toDTO(Favorito favorito) {
        return FavoritoDTO.builder()
                .id(favorito.getId())
                .filmeId(favorito.getFilme().getId())
                .usuarioId(favorito.getUsuarioId())
                .build();
    }
}