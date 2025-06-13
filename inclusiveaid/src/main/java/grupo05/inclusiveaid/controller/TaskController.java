package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.TaskDTO;
import grupo05.inclusiveaid.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tarefas")
@Tag(name = "Tarefas", description = "APIs para gerenciamento de tarefas")
@SecurityRequirement(name = "bearerAuth")
public class TaskController extends BaseCrudController<TaskDTO> {
    public TaskController(TaskService service) {
        super(service);
    }
}
