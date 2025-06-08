package grupo05.inclusiveaid.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grupo05.inclusiveaid.entity.Tarefa;
import grupo05.inclusiveaid.service.TarefaService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tarefa")
@RequiredArgsConstructor
public class TarefaController {
    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> createTarefa(@RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(tarefaService.createTarefa(tarefa));
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> getAllTarefas() {
        List<Tarefa> list = tarefaService.getAllTarefas();
        if (list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTarefaById(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.getTarefaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> updateTarefaById(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(tarefaService.updateTarefa(id, tarefa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTarefaById(@PathVariable Long id) {
        tarefaService.deleteTarefa(id);
        return ResponseEntity.ok("Tarefa deletada");
    }
}
