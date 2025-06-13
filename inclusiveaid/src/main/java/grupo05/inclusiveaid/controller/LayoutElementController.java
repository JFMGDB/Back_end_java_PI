package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.LayoutElementDTO;
import grupo05.inclusiveaid.service.LayoutElementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/layout-elements")
@Tag(name = "Elementos de Layout", description = "APIs para gerenciamento de elementos de layout")
public class LayoutElementController extends BaseCrudController<LayoutElementDTO> {
    public LayoutElementController(LayoutElementService service) {
        super(service);
    }
} 