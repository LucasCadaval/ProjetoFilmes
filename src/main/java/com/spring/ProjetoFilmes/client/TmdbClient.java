package com.spring.ProjetoFilmes.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

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

        } while (pagina <= totalPaginas && pagina <= 10); // Limite de 10 páginas para evitar sobrecarga

        return todosFilmes;
    }

}
