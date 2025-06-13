package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.LanguageDTO;
import grupo05.inclusiveaid.service.LanguageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
* Controlador REST para Idiomas. 
*/
@RestController
@RequestMapping("/api/v1/languages")
@Tag(name = "Idiomas", description = "APIs para gerenciamento de idiomas")
@SecurityRequirement(name = "bearerAuth")
public class LanguageController extends BaseCrudController<LanguageDTO> {
    public LanguageController(LanguageService service) {
        super(service);
    }
}