package com.spring.ProjetoFilmes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FilmeDTO {

    private Long id;

    private String posterUrl;

    private String posterPath;

    private String title;

    private String overview;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("vote_average")
    private Double voteAverage;

    private List<Genre> genres;

    private Boolean isFavorito;
    private Double mediaAvaliacoes;

    @Data
    @Builder
    public static class Genre {
        private Long id;
        private String name;
    }
}
