package com.spring.ProjetoFilmes.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class TmdbClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String TMDB_API_KEY = "e4c36220a6c9575c055451d0c6e16da6";
    private final String TMDB_BASE_URL = "https://api.themoviedb.org/3";

    public JsonNode buscarFilmePorId(Long tmdbId) {
        String url = UriComponentsBuilder.fromHttpUrl(TMDB_BASE_URL + "/movie/" + tmdbId)
                .queryParam("api_key", TMDB_API_KEY)
                .queryParam("language", "pt-BR")
                .toUriString();

        return restTemplate.getForObject(url, JsonNode.class);
    }

    public JsonNode buscarFilmesPopulares() {
        String url = UriComponentsBuilder.fromHttpUrl(TMDB_BASE_URL + "/movie/popular")
                .queryParam("api_key", TMDB_API_KEY)
                .queryParam("language", "pt-BR")
                .toUriString();

        return restTemplate.getForObject(url, JsonNode.class);
    }

    public JsonNode buscarFilmesPorGenero(int generoId) {
        String url = UriComponentsBuilder.fromHttpUrl(TMDB_BASE_URL + "/discover/movie")
                .queryParam("api_key", TMDB_API_KEY)
                .queryParam("language", "pt-BR")
                .queryParam("with_genres", generoId)
                .toUriString();

        return restTemplate.getForObject(url, JsonNode.class);
    }

    public List<JsonNode> buscarTodosFilmesPopulares() {
        List<JsonNode> todosFilmes = new ArrayList<>();
        int pagina = 1;
        int totalPaginas;

        do {
            String url = UriComponentsBuilder.fromHttpUrl(TMDB_BASE_URL + "/movie/popular")
                    .queryParam("api_key", TMDB_API_KEY)
                    .queryParam("language", "pt-BR")
                    .queryParam("page", pagina)
                    .toUriString();

            JsonNode resposta = restTemplate.getForObject(url, JsonNode.class);
            totalPaginas = resposta.get("total_pages").asInt();

            for (JsonNode filme : resposta.get("results")) {
                todosFilmes.add(filme);
            }

            pagina++;

        } while (pagina <= totalPaginas && pagina <= 10);

        return todosFilmes;
    }

    public List<JsonNode> buscarFilmesPorGenero(Long generoId) {
        String url = TMDB_BASE_URL + "/discover/movie?with_genres=" + generoId + "&api_key=" + TMDB_API_KEY + "&language=pt-BR";
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);
        return StreamSupport
                .stream(response.get("results").spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<JsonNode> buscarFilmesPorNome(String nome) {
        String url = TMDB_BASE_URL + "/search/movie?query=" + UriUtils.encode(nome, StandardCharsets.UTF_8)
                + "&api_key=" + TMDB_API_KEY + "&language=pt-BR";
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);

        List<JsonNode> filmes = new ArrayList<>();
        if (response.has("results") && response.get("results").isArray()) {
            for (JsonNode node : response.get("results")) {
                filmes.add(node);
            }
        }
        return filmes;
    }

    public Map<Long, String> buscarMapaDeGeneros() {
        String url = TMDB_BASE_URL + "/genre/movie/list?api_key=" + TMDB_API_KEY + "&language=pt-BR";
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);

        Map<Long, String> mapa = new HashMap<>();
        if (response.has("genres") && response.get("genres").isArray()) {
            for (JsonNode genero : response.get("genres")) {
                mapa.put(genero.get("id").asLong(), genero.get("name").asText());
            }
        }
        return mapa;
    }
}
