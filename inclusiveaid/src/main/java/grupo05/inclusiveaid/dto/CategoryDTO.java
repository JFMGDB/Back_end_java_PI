package grupo05.inclusiveaid.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO para Categoria.
 */
@Data
@Schema(description = "Data Transfer Object for Category operations")
public class CategoryDTO {
    @Schema(description = "Unique identifier of the category", example = "1")
    private Long id;
    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must be up to 50 characters")
    @Schema(description = "Name of the category", example = "Audio")
    private String name;
}