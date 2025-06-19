package com.spring.ProjetoFilmes.controller;

import com.spring.ProjetoFilmes.dto.FilmeDTO;
import com.spring.ProjetoFilmes.services.FilmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
@RequiredArgsConstructor
public class FilmeController {

    private final FilmeService filmeService;

    @GetMapping("/{id}")
    public ResponseEntity<FilmeDTO> buscarPorId(
            @PathVariable Long id,
            @RequestParam Long usuarioId
    ) {
        return ResponseEntity.ok(filmeService.buscarPorId(id, usuarioId));
    }

    @GetMapping("/populares")
    public ResponseEntity<List<FilmeDTO>> listarPopulares(
            @RequestParam Long usuarioId
    ) {
        return ResponseEntity.ok(filmeService.listarPopulares(usuarioId));
    }
}
