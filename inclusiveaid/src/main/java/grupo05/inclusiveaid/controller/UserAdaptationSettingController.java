package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.UserAdaptationSettingDTO;
import grupo05.inclusiveaid.service.UserAdaptationSettingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-adaptation-settings")
@Tag(name = "Configurações de Adaptação do Usuário", description = "APIs para gerenciamento de preferências de adaptação de usuários")
public class UserAdaptationSettingController extends BaseCrudController<UserAdaptationSettingDTO> {
    public UserAdaptationSettingController(UserAdaptationSettingService service) {
        super(service);
    }
} 