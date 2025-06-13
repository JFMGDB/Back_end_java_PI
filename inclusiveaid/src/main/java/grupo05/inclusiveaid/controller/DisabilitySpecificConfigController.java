package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.DisabilitySpecificConfigDTO;
import grupo05.inclusiveaid.service.DisabilitySpecificConfigService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller REST para configurações específicas de deficiência.
 */
@RestController
@RequestMapping("/api/disability-specific-configs")
@Tag(name = "Config. Espec. Deficiência", description = "CRUD para configurações específicas de deficiência de um agente IA")
public class DisabilitySpecificConfigController extends BaseCrudController<DisabilitySpecificConfigDTO> {

    public DisabilitySpecificConfigController(DisabilitySpecificConfigService service) {
        super(service);
    }
} 