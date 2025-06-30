package com.spring.ProjetoFilmes.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "usuario_id")
    private Long usuarioId;

    private String email;

    private String senha;
}
