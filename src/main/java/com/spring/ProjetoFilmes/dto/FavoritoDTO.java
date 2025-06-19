package com.spring.ProjetoFilmes.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FavoritoDTO {
    private Long id;
    private Long filmeId;
    private Long usuarioId;
}
