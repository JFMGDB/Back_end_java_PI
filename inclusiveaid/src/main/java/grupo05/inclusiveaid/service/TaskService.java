package grupo05.inclusiveaid.service;

import grupo05.inclusiveaid.dto.TaskDTO;
import org.springframework.data.domain.Page;

/**
 * Interface que define os serviços relacionados às tarefas.
 * Contém os métodos para operações CRUD (Criar, Ler, Atualizar, Deletar) de tarefas.
 */
public interface TaskService {
    /**
     * Cria uma nova tarefa.
     * @param dto DTO da tarefa a ser criada
     * @return DTO da tarefa criada
     */
    TaskDTO create(TaskDTO dto);

    /**
     * Busca uma tarefa pelo ID.
     * @param id ID da tarefa
     * @return DTO da tarefa encontrada
     */
    TaskDTO getById(Long id);

    /**
     * Retorna todas as tarefas cadastradas.
     * @param page Número da página
     * @param size Tamanho da página
     * @return Página de DTOs de tarefas
     */
    Page<TaskDTO> listAll(int page, int size);

    /**
     * Atualiza uma tarefa existente.
     * @param id ID da tarefa
     * @param dto DTO dos novos dados da tarefa
     * @return DTO da tarefa atualizada
     */
    TaskDTO update(Long id, TaskDTO dto);

    /**
     * Remove uma tarefa pelo ID.
     * @param id ID da tarefa a ser removida
     */
    void delete(Long id);
}
