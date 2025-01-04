package com.senacead.NotChicken2.controller;

import com.senacead.NotChicken2.model.Exercicio;
import com.senacead.NotChicken2.service.ExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exercicios")
public class ExercicioRestController {

    @Autowired
    private ExercicioService exercicioService;

    @GetMapping
    public List<Exercicio> listarExercicios() {
        return exercicioService.listarExercicios();
    }

    @PostMapping
    public Exercicio adicionarExercicio(@RequestBody Exercicio exercicio) {
        return exercicioService.adicionarExercicio(exercicio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercicio> buscarExercicio(@PathVariable Long id) {
        Optional<Exercicio> exercicio = exercicioService.buscarExercicio(id);
        return exercicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercicio> atualizarExercicio(@PathVariable Long id, @RequestBody Exercicio exercicioAtualizado) {
        Exercicio exercicio = exercicioService.atualizarExercicio(id, exercicioAtualizado);
        return exercicio != null ? ResponseEntity.ok(exercicio) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarExercicio(@PathVariable Long id) {
        boolean deletado = exercicioService.deletarExercicio(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
