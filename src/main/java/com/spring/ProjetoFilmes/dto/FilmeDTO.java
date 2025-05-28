package com.spring.ProjetoFilmes.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import javax.validation.constraints.*;

@Data
public class FilmeDTO {
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Diretor é obrigatório")
    private String diretor;

    @NotBlank(message = "Gênero é obrigatório")
    private String genero;

    private String sinopse;

    @Min(1900)
    private int anoLancamento;


    private Double mediaAvaliacoes;
    private Boolean isFavorito;
}