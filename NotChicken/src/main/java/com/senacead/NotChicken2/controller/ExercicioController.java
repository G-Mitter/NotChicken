package com.senacead.NotChicken2.controller;

import com.senacead.NotChicken2.model.Exercicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExercicioController {

    private List<Exercicio> exercicios = new ArrayList<>();

    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Essa aqui vai retornar a página de listar exercícios 
    @GetMapping("/Exercicios")
    public String listarExercicios(Model model) {
        model.addAttribute("Exercicios", exercicios);
        return "listarExercicios";
    }

    @GetMapping("/Exercicios/novo")
    public String novoExercicio(Model model) {
        model.addAttribute("Exercicio", new Exercicio());
        return "cadastrarExercicio";
    }

    @PostMapping("/Exercicios")
    public String cadastrarExercicios(Exercicio exercicio) {
        exercicio.setId((long) (exercicios.size() + 1));
        exercicios.add(exercicio);

        // Salvar no arquivo JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("exercicios.json"), exercicios);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/Exercicios";
    }

    @GetMapping("/Exercicios/detalhes")
    public String detalhesExercicio(@RequestParam("id") Long id, Model model) {
        Exercicio exercicio = exercicios.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("Exercicio", exercicio);
        return "detalhesExercicio";
    }
    @GetMapping("/calcularImc")
    public String mostrarCalculoImc() {
        return "calcularImc";
    }

    @PostMapping("/calcularImc")
    public String calcularImc(@RequestParam("peso") double peso, @RequestParam("altura") double altura, Model model) {
        double imc = peso / (altura * altura);
        model.addAttribute("imc", imc);
        return "calcularImc";
    }
}
