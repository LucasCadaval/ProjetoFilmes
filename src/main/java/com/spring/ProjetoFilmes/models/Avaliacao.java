package com.spring.ProjetoFilmes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    //id de filme é da tmdb api
    private Long filmeId;

    @NotNull
    @DecimalMin(value = "0.0", message = "Nota mínima é 0")
    @DecimalMax(value = "5.0", message = "Nota máxima é 5")
    private Double nota;


    private String comentario;
}
