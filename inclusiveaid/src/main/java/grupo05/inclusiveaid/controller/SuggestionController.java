package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.SuggestionDTO;
import grupo05.inclusiveaid.service.SuggestionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/suggestions")
@Tag(name = "Sugestões", description = "APIs para gerenciamento de sugestões")
@SecurityRequirement(name = "bearerAuth")
public class SuggestionController extends BaseCrudController<SuggestionDTO> {
    public SuggestionController(SuggestionService service) {
        super(service);
    }
}