package com.spring.ProjetoFilmes.dto;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class AvaliacaoDTO {
    private Long id;

    @NotNull(message = "Filme ID é obrigatório")
    private Long filmeId;

    @Min(1) @Max(5)
    private int nota;

    @Size(max = 500)
    private String comentario;

    private Long usuarioId;
    private String usuarioNome;
}