package grupo05.inclusiveaid.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO para Idioma.
 */
@Data
@Schema(description = "Data Transfer Object for Language operations")
public class LanguageDTO {
    @Schema(description = "Unique identifier of the language", example = "1")
    private Long id;
@NotBlank(message = "Code is required") 
@Size(min = 2, max = 10, message = "Code must be between 2 and 10 
characters") 
@Schema(description = "ISO code of the language", example = "pt") 
private String code;
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be up to 100 characters")
    @Schema(description = "Name of the language", example = "Português")
    private String name;
}