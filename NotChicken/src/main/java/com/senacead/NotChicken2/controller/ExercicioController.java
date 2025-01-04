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
    public String inicio() {
        return "index";
    }

    @GetMapping("/exercicios")
    public String listarExercicios(Model model) {
        List<Exercicio> exercicios = exercicioService.listarExercicios();
        model.addAttribute("exercicios", exercicios);
        return "listarExercicios";
    }

    @GetMapping("/exercicios/detalhes")
    public String detalhesExercicio(@RequestParam("id") Long id, Model model) {
        Exercicio exercicio = exercicioService.buscarExercicio(id).orElse(null);
        model.addAttribute("exercicio", exercicio);
        return "detalhesExercicio";
    }

    @GetMapping("/exercicios/novo")
    public String novoExercicio(Model model) {
        model.addAttribute("exercicio", new Exercicio());
        return "cadastrarExercicio";
    }

    @GetMapping("/exercicios/editar/{id}")
    public String editarExercicio(@PathVariable Long id, Model model) {
        Exercicio exercicio = exercicioService.buscarExercicio(id).orElse(null);
        model.addAttribute("exercicio", exercicio);
        return "cadastrarExercicio";
    }

    @PostMapping("/exercicios")
    public String adicionarExercicio(@ModelAttribute Exercicio exercicio) {
        exercicioService.adicionarExercicio(exercicio);
        return "redirect:/exercicios";
    }

    @PutMapping("/exercicios/atualizar/{id}")
    public String atualizarExercicio(@PathVariable Long id, @ModelAttribute Exercicio exercicioAtualizado) {
        exercicioService.atualizarExercicio(id, exercicioAtualizado);
        return "redirect:/exercicios/detalhes?id=" + id;
    }

    @PostMapping("/exercicios/deletar/{id}")
    public String deletarExercicio(@PathVariable Long id) {
        exercicioService.deletarExercicio(id);
        return "redirect:/exercicios";
    }

    @GetMapping("/calcularImc")
    public String calcularImcForm() {
        return "calcularImc";
    }

    @PostMapping("/calcularImc")
    public String calcularImc(@RequestParam("peso") double peso, @RequestParam("altura") double altura, Model model) {
        double imc = peso / (altura * altura);
        model.addAttribute("imc", imc);
        return "calcularImc";
    }
}
