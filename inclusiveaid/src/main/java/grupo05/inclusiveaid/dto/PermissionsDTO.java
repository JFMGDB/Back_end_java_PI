package grupo05.inclusiveaid.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "DTO para gerenciamento de permissões de usuários")
public class PermissionsDTO {
    
    /**
     * Identificador único da permissão.
     */
    @Schema(description = "ID único da permissão", example = "1")
    private Long id;

    /**
     * Nome da permissão.
     * Não pode estar em branco.
     */
    @NotBlank(message = "O nome da permissão é obrigatório", groups = {Create.class, Update.class})
    @Schema(description = "Nome da permissão", example = "ADMIN", required = true)
    private String name;

    /**
     * Descrição detalhada da permissão.
     * Não pode estar em branco.
     */
    @NotBlank(message = "A descrição da permissão é obrigatória", groups = {Create.class, Update.class})
    @Schema(description = "Descrição detalhada da permissão", example = "Permissão de administrador com acesso total ao sistema")
    private String description;

    @NotNull(message = "O status da permissão é obrigatório", groups = {Create.class, Update.class})
    @Schema(description = "Status da permissão (ativo/inativo)", example = "true")
    private Boolean active;

    public interface Create {}
    public interface Update {}
} 