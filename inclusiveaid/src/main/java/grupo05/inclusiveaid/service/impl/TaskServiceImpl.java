package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.TaskDTO;
import grupo05.inclusiveaid.entity.Task;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.TaskMapper;
import grupo05.inclusiveaid.repository.TaskRepository;
import grupo05.inclusiveaid.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviço responsável por gerenciar tarefas criadas pelos usuários do sistema.
 * <p>
 * Provê operações CRUD e listagem paginada, garantindo validação de existência e
 * conversão entre entidades e DTOs por meio do {@link TaskMapper}.
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    /**
     * Cria uma nova tarefa.
     *
     * @param dto dados da tarefa
     * @return tarefa criada em formato DTO
     */
    @Override
    @Transactional
    public TaskDTO create(TaskDTO dto) {
        Task task = taskMapper.toEntity(dto);
        return taskMapper.toDto(taskRepository.save(task));
    }

    /**
     * Recupera uma tarefa pelo ID.
     *
     * @param id identificador da tarefa
     * @return DTO da tarefa encontrada
     * @throws ResourceNotFoundException caso a tarefa não exista
     */
    @Override
    public TaskDTO getById(Long id) {
        return taskMapper.toDto(taskRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found")));
    }

    /**
     * Lista tarefas paginadas.
     *
     * @param page número da página
     * @param size tamanho da página
     * @return página de tarefas convertidas em DTO
     */
    @Override
    public Page<TaskDTO> listAll(int page, int size) {
        return taskRepository.findAll(PageRequest.of(page, size))
            .map(taskMapper::toDto);
    }

    /**
     * Atualiza uma tarefa existente.
     *
     * @param id  identificador da tarefa
     * @param dto novos dados da tarefa
     * @return tarefa atualizada em DTO
     * @throws ResourceNotFoundException caso a tarefa não exista
     */
    @Override
    @Transactional
    public TaskDTO update(Long id, TaskDTO dto) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found");
        }
        
        Task task = taskMapper.toEntity(dto);
        task.setId(id);
        return taskMapper.toDto(taskRepository.save(task));
    }

    /**
     * Remove uma tarefa pelo ID.
     *
     * @param id identificador da tarefa
     * @throws ResourceNotFoundException caso a tarefa não exista
     */
    @Override
    @Transactional
    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found");
        }
        taskRepository.deleteById(id);
    }
}
