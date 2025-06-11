package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório para operações de banco de dados relacionadas às tarefas.
 * Fornece métodos para persistência e consulta de tarefas.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * Busca tarefas por responsável.
     * @param responsibleId ID do responsável
     * @return Lista de tarefas associadas ao responsável
     */
    List<Task> findByResponsibleId(Long responsibleId);
    
    /**
     * Busca tarefas por status.
     * @param status Status das tarefas
     * @return Lista de tarefas com o status especificado
     */
    List<Task> findByStatus(String status);
}
