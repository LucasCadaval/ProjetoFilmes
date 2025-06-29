package com.spring.ProjetoFilmes.controller;

import com.spring.ProjetoFilmes.dto.AvaliacaoDTO;
import com.spring.ProjetoFilmes.models.Avaliacao;
import com.spring.ProjetoFilmes.services.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<Avaliacao> cadastrar(@RequestBody @Valid AvaliacaoDTO dto) {
        if (dto.getNota() < 0 || dto.getNota() > 5) {
            return ResponseEntity.badRequest().body(null);
        }
        Avaliacao nova = avaliacaoService.cadastrar(dto);
        return ResponseEntity.ok(nova);
    }


    @GetMapping("/filme/{filmeId}")
    public ResponseEntity<List<Avaliacao>> listarPorFilme(@PathVariable Long filmeId) {
        return ResponseEntity.ok(avaliacaoService.listarPorFilme(filmeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        avaliacaoService.remover(id);
        return ResponseEntity.noContent().build();
    }

}
