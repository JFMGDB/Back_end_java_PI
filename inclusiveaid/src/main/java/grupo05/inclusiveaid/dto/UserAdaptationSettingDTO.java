package grupo05.inclusiveaid.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * DTO para configurações de adaptação do usuário.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAdaptationSettingDTO {
    public interface Create {}
    public interface Update {}

    private Long id;

    @NotNull(groups = {Create.class, Update.class})
    private Long userId;

    @NotNull(groups = {Create.class, Update.class})
    private Long adaptationId;

    private boolean enabled;
} 