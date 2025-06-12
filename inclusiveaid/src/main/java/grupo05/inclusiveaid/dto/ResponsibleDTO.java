package grupo05.inclusiveaid.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) para responsáveis.
 * Utilizado para transferir dados relacionados aos responsáveis por usuários ou tarefas no sistema.
 *
 * @author Grupo 05
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsibleDTO {
    /**
     * Identificador único do responsável.
     */
    private Long id;

    /**
     * Nome do responsável.
     * Não pode estar em branco.
     */
    @NotBlank(groups = {Create.class, Update.class})
    private String name;

    /**
     * Email do responsável.
     * Não pode estar em branco.
     */
    @NotBlank(groups = {Create.class, Update.class})
    @Email(groups = {Create.class, Update.class})
    private String email;

    @JsonIgnore
    @NotBlank(groups = {Create.class})
    private String password;

    /**
     * Telefone de contato do responsável.
     */
    @NotBlank(groups = {Create.class, Update.class})
    private String phone;

    @NotNull(groups = {Create.class, Update.class})
    private Long userId;

    public interface Create {}
    public interface Update {}
} 