package com.spring.ProjetoFilmes.services;

import com.spring.ProjetoFilmes.dto.FavoritoDTO;
import com.spring.ProjetoFilmes.models.Favorito;
import com.spring.ProjetoFilmes.repository.FavoritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;

    public FavoritoDTO marcarFavorito(Long usuarioId, Long filmeId) {
        if (favoritoRepository.existsByUsuarioIdAndFilmeId(usuarioId, filmeId)) {
            throw new RuntimeException("Filme já está nos favoritos");
        }

        Favorito favorito = new Favorito();
        favorito.setUsuarioId(usuarioId);
        favorito.setFilmeId(filmeId);

        Favorito favoritoSalvo = favoritoRepository.save(favorito);
        return toDTO(favoritoSalvo);
    }

    public boolean isFavorito(Long usuarioId, Long filmeId) {
        return favoritoRepository.existsByUsuarioIdAndFilmeId(usuarioId, filmeId);
    }

    private FavoritoDTO toDTO(Favorito favorito) {
        return FavoritoDTO.builder()
                .id(favorito.getId())
                .filmeId(favorito.getFilmeId())
                .usuarioId(favorito.getUsuarioId())
                .build();
    }
}
