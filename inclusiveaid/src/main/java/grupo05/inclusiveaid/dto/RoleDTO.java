package grupo05.inclusiveaid.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

/**
 * DTO para papéis de usuário.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    public interface Create {}
    public interface Update {}

    private Long id;

    @NotBlank(groups = {Create.class, Update.class})
    @Pattern(regexp = "^ROLE_[A-Z]+$", message = "O nome do papel deve seguir o padrão ROLE_*", groups = {Create.class, Update.class})
    private String name;
} 