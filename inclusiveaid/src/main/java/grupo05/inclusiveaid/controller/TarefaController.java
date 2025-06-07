package com.seuusuario.seuprojeto.controller;

import com.seuusuario.seuprojeto.model.Tarefa;
import com.seuusuario.seuprojeto.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping
    public List<Tarefa> listar() {
        return tarefaRepository.findAll();
    }

    @PostMapping
    public Tarefa salvar(@RequestBody Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        tarefaRepository.deleteById(id);
    }
}
