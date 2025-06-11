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
 * Implementação do serviço de tarefas.
 * Contém a lógica de negócio para operações relacionadas às tarefas.
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    @Transactional
    public TaskDTO create(TaskDTO dto) {
        Task task = taskMapper.toEntity(dto);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskDTO getById(Long id) {
        return taskMapper.toDto(taskRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found")));
    }

    @Override
    public Page<TaskDTO> listAll(int page, int size) {
        return taskRepository.findAll(PageRequest.of(page, size))
            .map(taskMapper::toDto);
    }

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

    @Override
    @Transactional
    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found");
        }
        taskRepository.deleteById(id);
    }
}
