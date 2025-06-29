package com.spring.ProjetoFilmes.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.spring.ProjetoFilmes.client.TmdbClient;
import com.spring.ProjetoFilmes.dto.FilmeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final TmdbClient tmdbClient;
    private final AvaliacaoService avaliacaoService;
    private final FavoritoService favoritoService;

    public FilmeDTO buscarPorId(Long id, Long usuarioId) {
        JsonNode json = tmdbClient.buscarFilmePorId(id);

        return FilmeDTO.builder()
                .id(json.get("id").asLong())
                .title(json.get("title").asText())
                .overview(json.get("overview").asText())
                .releaseDate(json.get("release_date").asText())
                .voteAverage(json.has("vote_average") ? json.get("vote_average").asDouble() : null)
                .genres(json.has("genres") ? converterGeneros(json.get("genres")) : new ArrayList<>())
                .isFavorito(favoritoService.isFavorito(usuarioId, id))
                .mediaAvaliacoes(avaliacaoService.calcularMediaAvaliacoes(id))
                .build();
    }

    public List<FilmeDTO> listarPopulares(Long usuarioId) {
        List<JsonNode> filmesJson = tmdbClient.buscarTodosFilmesPopulares();
        List<FilmeDTO> filmes = new ArrayList<>();

        for (JsonNode json : filmesJson) {
            Long filmeId = json.get("id").asLong();

            FilmeDTO dto = FilmeDTO.builder()
                    .id(filmeId)
                    .title(json.get("title").asText())
                    .overview(json.get("overview").asText())
                    .releaseDate(json.get("release_date").asText())
                    .voteAverage(json.has("vote_average") ? json.get("vote_average").asDouble() : null)
                    .genres(json.has("genre_ids") ? converterGenerosPorId(json.get("genre_ids")) : new ArrayList<>())
                    .isFavorito(favoritoService.isFavorito(usuarioId, filmeId))
                    .mediaAvaliacoes(avaliacaoService.calcularMediaAvaliacoes(filmeId))
                    .build();

            filmes.add(dto);
        }

        return filmes;
    }

    private List<FilmeDTO.Genre> converterGeneros(JsonNode genresJson) {
        List<FilmeDTO.Genre> generos = new ArrayList<>();
        for (JsonNode genero : genresJson) {
            generos.add(FilmeDTO.Genre.builder()
                    .id(genero.get("id").asLong())
                    .name(genero.get("name").asText())
                    .build());
        }
        return generos;
    }

    private List<FilmeDTO.Genre> converterGenerosPorId(JsonNode genreIds) {
        List<FilmeDTO.Genre> generos = new ArrayList<>();
        for (JsonNode id : genreIds) {
            generos.add(FilmeDTO.Genre.builder()
                    .id(id.asLong())
                    .name("Gênero " + id.asLong()) // ou mapeie corretamente com uma tabela real
                    .build());
        }
        return generos;
    }
}
