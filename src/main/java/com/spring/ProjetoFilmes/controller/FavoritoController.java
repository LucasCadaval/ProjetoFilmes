package com.spring.ProjetoFilmes.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.spring.ProjetoFilmes.client.TmdbClient;
import com.spring.ProjetoFilmes.models.Avaliacao;
import com.spring.ProjetoFilmes.models.Favorito;
import com.spring.ProjetoFilmes.services.AvaliacaoService;
import com.spring.ProjetoFilmes.services.FavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FavoritoController {

    private final TmdbClient tmdbClient;
    private final FavoritoService favoritoService;
    private final AvaliacaoService avaliacaoService;

    @PostMapping("/favoritos")
    public ResponseEntity<Favorito> adicionar(@RequestParam Long usuarioId, @RequestParam Long filmeId) {
        return ResponseEntity.ok(favoritoService.adicionarFavorito(usuarioId, filmeId));
    }

    @DeleteMapping("/favoritos")
    public ResponseEntity<Void> remover(@RequestParam Long usuarioId, @RequestParam Long filmeId) {
        favoritoService.removerFavorito(usuarioId, filmeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/favoritos/{usuarioId}")
    public ResponseEntity<List<JsonNode>> listarFilmesFavoritos(@PathVariable Long usuarioId) {
        List<Favorito> favoritos = favoritoService.listarFavoritosPorUsuario(usuarioId);

        List<JsonNode> filmes = favoritos.stream()
                .map(fav -> tmdbClient.buscarFilmePorId(fav.getFilmeId()))
                .toList();

        return ResponseEntity.ok(filmes);
    }


    @GetMapping("/{usuarioId}/dados")
    public ResponseEntity<Map<String, Object>> getDadosDoUsuario(@PathVariable Long usuarioId) {
        List<Avaliacao> avaliacoes = avaliacaoService.listarPorUsuario(usuarioId);
        List<Favorito> favoritos = favoritoService.listarPorUsuario(usuarioId);

        Map<String, Object> response = new HashMap<>();
        response.put("avaliacoes", avaliacoes);
        response.put("favoritos", favoritos);

        return ResponseEntity.ok(response);
    }
}