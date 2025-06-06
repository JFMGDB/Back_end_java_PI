package grupo05.inclusiveaid.service;

import java.util.List;

import grupo05.inclusiveaid.entity.Task;

public interface TaskService {
    Task createTask(Task task);
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task updateTask(Long id, Task updateTask);
    void deleteTask(Long id);
}
