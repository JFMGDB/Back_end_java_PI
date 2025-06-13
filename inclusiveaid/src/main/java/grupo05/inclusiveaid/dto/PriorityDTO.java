package grupo05.inclusiveaid.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Data Transfer Object for Priority operations")
public class PriorityDTO {
    @Schema(description = "Unique identifier of the priority", example = "1")
    private Long id;
    @NotBlank(message = "Level is required")
    @Size(max = 20, message = "Level must be up to 20 characters")
    @Schema(description = "Level of the priority", example = "HIGH")
    private String level;
}