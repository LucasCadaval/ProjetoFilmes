package com.spring.ProjetoFilmes.services;

import com.spring.ProjetoFilmes.models.Favorito;
import com.spring.ProjetoFilmes.repository.FavoritoRepository;
import com.spring.ProjetoFilmes.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;

    private final UsuarioRepository usuarioRepository;


//    public Favorito adicionarFavorito(Long usuarioId, Long filmeId) {
//        Favorito favorito = Favorito.builder()
//                .usuarioId(usuarioId)
//                .filmeId(filmeId)
//                .build();
//        return favoritoRepository.save(favorito);
//    }

    public void removerFavorito(Long usuarioId, Long filmeId) {
        favoritoRepository.findByUsuarioIdAndFilmeId(usuarioId, filmeId)
                .ifPresent(favoritoRepository::delete);
    }

    public List<Favorito> listarFavoritosPorUsuario(Long usuarioId) {
        return favoritoRepository.findByUsuarioId(usuarioId);
    }

    public boolean isFavorito(Long usuarioId, Long filmeId) {
        return favoritoRepository.findByUsuarioIdAndFilmeId(usuarioId, filmeId).isPresent();
    }

    public List<Favorito> listarPorUsuario(Long usuarioId) {
        return favoritoRepository.findByUsuarioId(usuarioId);
    }

    public Favorito adicionarFavorito(Long usuarioId, Long filmeId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário não existe com ID: " + usuarioId);
        }

        Favorito favorito = Favorito.builder()
                .usuarioId(usuarioId)
                .filmeId(filmeId)
                .build();

        return favoritoRepository.save(favorito);
    }

}
