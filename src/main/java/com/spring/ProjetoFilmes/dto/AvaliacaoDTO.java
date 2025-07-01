package com.spring.ProjetoFilmes.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class AvaliacaoDTO {

    private Long id;

    private Long usuarioId;
    @NotNull
    private Long filmeId;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true, message = "A nota deve ser no mínimo 0")
    @DecimalMax(value = "5.0", inclusive = true, message = "A nota deve ser no máximo 5")
    private Double nota;

    private String comentario;

    private String nomeUsuario;
}
