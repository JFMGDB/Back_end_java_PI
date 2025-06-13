package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.AgentInteractionDTO;
import grupo05.inclusiveaid.service.AgentInteractionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agent-interactions")
@Tag(name = "Interações de Agente", description = "APIs para gerenciamento de interações do agente de IA")
public class AgentInteractionController extends BaseCrudController<AgentInteractionDTO> {
    public AgentInteractionController(AgentInteractionService service) {
        super(service);
    }
} 