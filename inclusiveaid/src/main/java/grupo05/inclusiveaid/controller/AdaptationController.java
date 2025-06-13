package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.AdaptationDTO;
import grupo05.inclusiveaid.service.AdaptationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável pelo gerenciamento de adaptações de acessibilidade no sistema InclusiveAID.
 * Fornece endpoints para criar, recuperar, listar, atualizar e excluir adaptações.
 * 
 * Este controlador permite:
 * - Cadastrar novas adaptações de acessibilidade no sistema
 * - Consultar adaptações existentes
 * - Listar todas as adaptações de forma paginada
 * - Atualizar informações de adaptações
 * - Remover adaptações do sistema
 * 
 * As adaptações são configurações específicas que melhoram a acessibilidade
 * do sistema para diferentes tipos de deficiência, como:
 * - Adaptações visuais (alto contraste, tamanho de fonte)
 * - Adaptações auditivas (legendas, descrições de áudio)
 * - Adaptações motoras (navegação por teclado, comandos de voz)
 * - Adaptações cognitivas (simplificação de interface, assistentes)
 * 
 * @author Grupo 05
 * @version 1.0
 */
@RestController
@RequestMapping("/api/adaptations")
@Tag(name = "Adaptações", description = "CRUD de adaptações de acessibilidade")
public class AdaptationController extends BaseCrudController<AdaptationDTO> {
    public AdaptationController(AdaptationService service) {
        super(service);
    }
}