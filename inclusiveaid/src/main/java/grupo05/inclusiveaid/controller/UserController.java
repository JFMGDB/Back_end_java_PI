package grupo05.inclusiveaid.controller;

import grupo05.inclusiveaid.dto.UserDTO;
import grupo05.inclusiveaid.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável pelo gerenciamento de usuários no sistema InclusiveAID.
 * Fornece endpoints REST para criar, recuperar, atualizar e excluir usuários.
 * 
 * Este controlador permite:
 * - Cadastrar novos usuários no sistema
 * - Consultar usuários existentes
 * - Listar todos os usuários de forma paginada
 * - Atualizar informações de usuários
 * - Remover usuários do sistema
 * 
 * Os usuários são entidades fundamentais do sistema, podendo ter diferentes
 * perfis e necessidades de acessibilidade associadas.
 * 
 * @author Grupo 05
 * @version 1.0
 */
@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Gerenciamento de Usuários", description = "CRUD de usuários do sistema")
@SecurityRequirement(name = "bearerAuth")
public class UserController extends BaseCrudController<UserDTO> {
    public UserController(UserService service) {
        super(service);
    }
}