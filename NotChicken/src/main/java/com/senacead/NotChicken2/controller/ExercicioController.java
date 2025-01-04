package com.senacead.NotChicken2.controller;

import com.senacead.NotChicken2.model.Exercicio;
import com.senacead.NotChicken2.service.ExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;

    @GetMapping("/")
    public String inicio(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, Model model) {
        model.addAttribute("css", tema);
        return "index";
    }

    @GetMapping("/exercicios")
    public String listarExercicios(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, Model model) {
        List<Exercicio> exercicios = exercicioService.listarExercicios();
        model.addAttribute("exercicios", exercicios);
        model.addAttribute("css", tema); // Adiciona o tema ao modelo
        return "listarExercicios";
    }

    @GetMapping("/exercicios/detalhes")
    public String detalhesExercicio(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, @RequestParam("id") Long id, Model model) {
        Exercicio exercicio = exercicioService.buscarExercicio(id).orElse(null);
        model.addAttribute("exercicio", exercicio);
        model.addAttribute("css", tema); // Adiciona o tema ao modelo
        return "detalhesExercicio";
    }

    @GetMapping("/exercicios/editar/{id}")
    public String editarExercicio(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, @PathVariable Long id, Model model) {
        Exercicio exercicio = exercicioService.buscarExercicio(id).orElse(null);
        model.addAttribute("exercicio", exercicio);
        model.addAttribute("css", tema); // Adiciona o tema ao modelo
        return "cadastrarExercicio";
    }

    @PostMapping("/exercicios")
    public String adicionarExercicio(@ModelAttribute Exercicio exercicio) {
        exercicioService.adicionarExercicio(exercicio);
        return "redirect:/exercicios";
    }

    @PutMapping("/exercicios/atualizar/{id}")
    public String atualizarExercicio(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, @PathVariable Long id, @ModelAttribute Exercicio exercicioAtualizado, Model model) {
        exercicioService.atualizarExercicio(id, exercicioAtualizado);
        model.addAttribute("css", tema); // Adiciona o tema ao modelo
        return "redirect:/exercicios/detalhes?id=" + id;
    }

    @PostMapping("/exercicios/deletar/{id}")
    public String deletarExercicio(@PathVariable Long id) {
        exercicioService.deletarExercicio(id);
        return "redirect:/exercicios";
    }
}
