package grupo05.inclusiveaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.*;

/**
 * DTO (Data Transfer Object) para transferência de dados de usuário.
 * Utilizado para transferir dados entre a camada de apresentação e a camada de serviço,
 * contendo informações pessoais, credenciais e preferências de acessibilidade.
 * 
 * @author Grupo 05
 * @version 1.0
 */
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de Transferência de Dados para operações de Usuário")
public class UserDTO {
    /**
     * Interface para validação na criação de um novo usuário.
     */
    public interface Create {}
    
    /**
     * Interface para validação na atualização de um usuário existente.
     */
    public interface Update {}

    @Schema(description = "Identificador único do usuário", example = "1")
    private Long id;

    @NotBlank(groups = {Create.class, Update.class}, message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @Schema(description = "Nome completo do usuário", example = "João Silva", required = true)
    private String name;

    @NotBlank(groups = {Create.class, Update.class}, message = "O email é obrigatório")
    @Email(groups = {Create.class, Update.class}, message = "Email inválido")
    @Schema(description = "Endereço de email do usuário", example = "joao.silva@exemplo.com", required = true)
    private String email;

    /**
     * Senha do usuário.
     * Obrigatória apenas na criação.
     * Não é serializada nas respostas JSON.
     */
    @NotBlank(groups = Create.class, message = "A senha é obrigatória")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * Identificador do papel/perfil do usuário no sistema.
     * Não pode ser nulo.
     */
    @NotNull(groups = {Create.class, Update.class})
    private Long roleId;

    /**
     * Conjunto de identificadores dos tipos de deficiência associados ao usuário.
     */
    @NotNull(groups = {Create.class, Update.class})
    @Schema(description = "IDs dos tipos de deficiência associados ao usuário", example = "[1,2]", required = true)
    private Set<Long> disabilityTypeIds;

    @Schema(description = "Observações adicionais sobre as necessidades do usuário", example = "Requer modo de alto contraste")
    private String notes;

    @Schema(description = "Indica se a conta do usuário está ativa", example = "true")
    private Boolean active;
}