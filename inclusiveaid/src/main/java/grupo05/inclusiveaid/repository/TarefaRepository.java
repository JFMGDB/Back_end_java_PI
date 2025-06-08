package grupo05.inclusiveaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import grupo05.inclusiveaid.entity.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
