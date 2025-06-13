package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.TagDTO;
import grupo05.inclusiveaid.service.TagService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para Tag.
 */
@RestController
@RequestMapping("/api/v1/tags")
@Tag(name = "Tags", description = "Endpoints para gerenciamento de tags")
@SecurityRequirement(name = "bearerAuth")
public class TagController extends BaseCrudController<TagDTO> {
    public TagController(TagService service) {
        super(service);
    }
}
