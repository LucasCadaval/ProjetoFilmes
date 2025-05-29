package com.spring.ProjetoFilmes.dto;

import lombok.Data;

@Data
public class FavoritoDTO {
    private Long id;
    private Long filmeId;
    private Long usuarioId;

    private FilmeDTO filme;
}