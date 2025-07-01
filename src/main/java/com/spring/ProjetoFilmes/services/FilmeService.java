package com.spring.ProjetoFilmes.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.spring.ProjetoFilmes.client.TmdbClient;
import com.spring.ProjetoFilmes.dto.FilmeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                .posterUrl(json.has("poster_path") && !json.get("poster_path").isNull()
                        ? "https://image.tmdb.org/t/p/w500" + json.get("poster_path").asText()
                        : null)
                .posterPath(json.has("poster_path") && !json.get("poster_path").isNull()
                        ? json.get("poster_path").asText()
                        : null)
                .genres(json.has("genres") ? converterGeneros(json.get("genres")) : new ArrayList<>())
                .isFavorito(favoritoService.isFavorito(usuarioId, id))
                .mediaAvaliacoes(avaliacaoService.calcularMediaAvaliacoes(id))
                .build();
    }

    public List<FilmeDTO> listarPopulares(Long usuarioId) {
        List<JsonNode> filmesJson = tmdbClient.buscarTodosFilmesPopulares();
        Map<Long, String> mapaDeGeneros = tmdbClient.buscarMapaDeGeneros();
        List<FilmeDTO> filmes = new ArrayList<>();

        for (JsonNode json : filmesJson) {
            Long filmeId = json.get("id").asLong();

            FilmeDTO dto = FilmeDTO.builder()
                    .id(filmeId)
                    .title(json.get("title").asText())
                    .overview(json.get("overview").asText())
                    .releaseDate(json.get("release_date").asText())
                    .voteAverage(json.has("vote_average") ? json.get("vote_average").asDouble() : null)
                    .posterUrl(json.has("poster_path") && !json.get("poster_path").isNull()
                            ? "https://image.tmdb.org/t/p/w500" + json.get("poster_path").asText()
                            : null)
                    .posterPath(json.has("poster_path") && !json.get("poster_path").isNull()
                            ? json.get("poster_path").asText()
                            : null)
                    .genres(json.has("genre_ids") ? converterGenerosPorId(json.get("genre_ids"), mapaDeGeneros) : new ArrayList<>())
                    .isFavorito(favoritoService.isFavorito(usuarioId, filmeId))
                    .mediaAvaliacoes(avaliacaoService.calcularMediaAvaliacoes(filmeId))
                    .build();

            filmes.add(dto);
        }

        return filmes;
    }

    public List<FilmeDTO> listarPorGenero(Long generoId, Long usuarioId) {
        List<JsonNode> filmesJson = tmdbClient.buscarFilmesPorGenero(generoId);
        Map<Long, String> mapaDeGeneros = tmdbClient.buscarMapaDeGeneros();
        List<FilmeDTO> filmes = new ArrayList<>();

        for (JsonNode json : filmesJson) {
            Long filmeId = json.get("id").asLong();

            FilmeDTO dto = FilmeDTO.builder()
                    .id(filmeId)
                    .title(json.get("title").asText())
                    .overview(json.get("overview").asText())
                    .releaseDate(json.get("release_date").asText())
                    .voteAverage(json.has("vote_average") ? json.get("vote_average").asDouble() : null)
                    .posterUrl(json.has("poster_path") && !json.get("poster_path").isNull()
                            ? "https://image.tmdb.org/t/p/w500" + json.get("poster_path").asText()
                            : null)
                    .posterPath(json.has("poster_path") && !json.get("poster_path").isNull()
                            ? json.get("poster_path").asText()
                            : null)
                    .genres(json.has("genre_ids") ? converterGenerosPorId(json.get("genre_ids"), mapaDeGeneros) : new ArrayList<>())
                    .isFavorito(favoritoService.isFavorito(usuarioId, filmeId))
                    .mediaAvaliacoes(avaliacaoService.calcularMediaAvaliacoes(filmeId))
                    .build();

            filmes.add(dto);
        }

        return filmes;
    }

    public List<FilmeDTO> buscarPorNome(String nome, Long usuarioId) {
        List<JsonNode> filmesJson = tmdbClient.buscarFilmesPorNome(nome);
        Map<Long, String> mapaDeGeneros = tmdbClient.buscarMapaDeGeneros();
        List<FilmeDTO> filmes = new ArrayList<>();

        for (JsonNode json : filmesJson) {
            Long filmeId = json.get("id").asLong();

            FilmeDTO dto = FilmeDTO.builder()
                    .id(filmeId)
                    .title(json.get("title").asText())
                    .overview(json.get("overview").asText())
                    .releaseDate(json.get("release_date").asText())
                    .voteAverage(json.has("vote_average") ? json.get("vote_average").asDouble() : null)
                    .posterUrl(json.has("poster_path") && !json.get("poster_path").isNull()
                            ? "https://image.tmdb.org/t/p/w500" + json.get("poster_path").asText()
                            : null)
                    .posterPath(json.has("poster_path") && !json.get("poster_path").isNull()
                            ? json.get("poster_path").asText()
                            : null)
                    .genres(json.has("genre_ids") ? converterGenerosPorId(json.get("genre_ids"), mapaDeGeneros) : new ArrayList<>())
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

    private List<FilmeDTO.Genre> converterGenerosPorId(JsonNode genreIds, Map<Long, String> mapaDeGeneros) {
        List<FilmeDTO.Genre> generos = new ArrayList<>();
        for (JsonNode id : genreIds) {
            long generoId = id.asLong();
            String nome = mapaDeGeneros.getOrDefault(generoId, "Gênero " + generoId);
            generos.add(FilmeDTO.Genre.builder()
                    .id(generoId)
                    .name(nome)
                    .build());
        }
        return generos;
    }
}
