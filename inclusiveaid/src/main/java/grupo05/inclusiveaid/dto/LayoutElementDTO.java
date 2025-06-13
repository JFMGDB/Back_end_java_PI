package grupo05.inclusiveaid.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * DTO para elementos de layout.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LayoutElementDTO {
    public interface Create {}
    public interface Update {}

    private Long id;

    @NotBlank(groups = {Create.class, Update.class})
    private String elementType;

    @NotBlank(groups = {Create.class, Update.class})
    private String description;

    @NotBlank(groups = {Create.class, Update.class})
    private String xpath;
} 