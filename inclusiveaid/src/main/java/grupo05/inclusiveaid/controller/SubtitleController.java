package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.SubtitleDTO;
import grupo05.inclusiveaid.service.SubtitleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável pelo gerenciamento de legendas de acessibilidade no sistema AID.
 * Fornece endpoints para criar, recuperar, listar, atualizar e excluir legendas para usuários com deficiência auditiva.
 */
@RestController
@RequestMapping("/api/subtitles")
@Tag(name = "Legenda", description = "APIs para gerenciamento de legendas de acessibilidade no sistema AID")
public class SubtitleController extends BaseCrudController<SubtitleDTO> {

    public SubtitleController(SubtitleService service) {
        super(service);
    }
}
