package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.RoleDTO;
import grupo05.inclusiveaid.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Papéis", description = "APIs para gerenciamento de papéis de usuários")
public class RoleController extends BaseCrudController<RoleDTO> {
    public RoleController(RoleService service) {
        super(service);
    }
} 