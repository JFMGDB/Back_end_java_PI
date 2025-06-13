package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.PriorityDTO;
import grupo05.inclusiveaid.service.PriorityService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/priorities")
@Tag(name = "Prioridades", description = "APIs para gerenciamento de prioridades")
@SecurityRequirement(name = "bearerAuth")
public class PriorityController extends BaseCrudController<PriorityDTO> {
    public PriorityController(PriorityService service) {
        super(service);
    }
} 