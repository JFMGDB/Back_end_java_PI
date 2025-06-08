package grupo05.inclusiveaid.service;

import java.util.List;
import grupo05.inclusiveaid.entity.Tarefa;

public interface TarefaService {
    Tarefa createTarefa(Tarefa tarefa);
    List<Tarefa> getAllTarefas();
    Tarefa getTarefaById(Long id);
    Tarefa updateTarefa(Long id, Tarefa updateTarefa);
    void deleteTarefa(Long id);
}
