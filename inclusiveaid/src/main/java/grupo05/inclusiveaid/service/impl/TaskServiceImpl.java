package grupo05.inclusiveaid.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import grupo05.inclusiveaid.entity.Task;
import grupo05.inclusiveaid.repository.TaskRepository;
import grupo05.inclusiveaid.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    public final TaskRepository taskRepository;

    @Override
    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id){
        return taskRepository
               .findById(id)
               .orElseThrow(() -> new EntityNotFoundException("Task não encontrada"));
    }

    @Override
    public Task updateTask(Long id, Task updateTask){
        Task task = getTaskById(id);
        task.setTitle(updateTask.getTitle() != null ? updateTask.getTitle() : task.getTitle());
        task.setDescription(updateTask.getDescription() != null ? updateTask.getDescription() : task.getDescription());

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id){
        getTaskById(id);
        taskRepository.deleteById(id);
    }
}
