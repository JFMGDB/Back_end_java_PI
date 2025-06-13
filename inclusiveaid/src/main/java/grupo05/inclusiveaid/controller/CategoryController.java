package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.CategoryDTO;
import grupo05.inclusiveaid.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "Categorias", description = "APIs para gerenciamento de categorias")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController extends BaseCrudController<CategoryDTO> {
    public CategoryController(CategoryService service) {
        super(service);
    }
}