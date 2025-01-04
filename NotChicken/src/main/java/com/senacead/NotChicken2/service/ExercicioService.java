package com.senacead.NotChicken2.service;

import com.senacead.NotChicken2.model.Exercicio;
import com.senacead.NotChicken2.repository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    public List<Exercicio> listarExercicios() {
        return exercicioRepository.findAll();
    }

    public Exercicio adicionarExercicio(Exercicio exercicio) {
        return exercicioRepository.save(exercicio);
    }

    public Optional<Exercicio> buscarExercicio(Long id) {
        return exercicioRepository.findById(id);
    }

    public Exercicio atualizarExercicio(Long id, Exercicio exercicioAtualizado) {
        Optional<Exercicio> exercicioOpt = exercicioRepository.findById(id);
        if (exercicioOpt.isPresent()) {
            Exercicio exercicio = exercicioOpt.get();
            exercicio.setNome(exercicioAtualizado.getNome());
            exercicio.setComoSeRealiza(exercicioAtualizado.getComoSeRealiza());
            exercicio.setGrupoMuscular(exercicioAtualizado.getGrupoMuscular());
            return exercicioRepository.save(exercicio);
        }
        return null;
    }

    public boolean deletarExercicio(Long id) {
        Optional<Exercicio> exercicio = exercicioRepository.findById(id);
        if (exercicio.isPresent()) {
            exercicioRepository.delete(exercicio.get());
            return true;
        }
        return false;
    }
}
