package grupo05.inclusiveaid.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import grupo05.inclusiveaid.entity.Tarefa;
import grupo05.inclusiveaid.repository.TarefaRepository;
import grupo05.inclusiveaid.service.TarefaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaServiceImpl implements TarefaService {
    private final TarefaRepository tarefaRepository;

    @Override
    public Tarefa createTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    @Override
    public List<Tarefa> getAllTarefas() {
        return tarefaRepository.findAll();
    }

    @Override
    public Tarefa getTarefaById(Long id) {
        return tarefaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));
    }

    @Override
    public Tarefa updateTarefa(Long id, Tarefa updateTarefa) {
        Tarefa tarefa = getTarefaById(id);
        tarefa.setTitulo(updateTarefa.getTitulo() != null ? updateTarefa.getTitulo() : tarefa.getTitulo());
        tarefa.setDescricao(updateTarefa.getDescricao() != null ? updateTarefa.getDescricao() : tarefa.getDescricao());

        return tarefaRepository.save(tarefa);
    }

    @Override
    public void deleteTarefa(Long id) {
        getTarefaById(id); // lança exceção se não existir
        tarefaRepository.deleteById(id);
    }
}
