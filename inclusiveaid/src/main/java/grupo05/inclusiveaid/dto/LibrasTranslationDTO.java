package grupo05.inclusiveaid.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * DTO para transferência de dados de tradução em Libras.
 */
@Data @Builder
@Schema(description = "Data Transfer Object for Libras Translation operations")
public class LibrasTranslationDTO {
    public interface Create {}
    public interface Update {}

    @Schema(description = "Unique identifier of the translation", example = "1")
    private Long id;

    @NotBlank(groups = {Create.class, Update.class}, message = "O texto original é obrigatório")
    @Size(min = 1, max = 1000, message = "O texto deve ter entre 1 e 1000 caracteres")
    @Schema(description = "Original text to be translated to Libras", example = "Olá, como você está?", required = true)
    private String originalText;

    @NotBlank(groups = {Create.class, Update.class}, message = "A URL da animação é obrigatória")
    @Schema(description = "URL of the avatar animation in Libras", example = "https://example.com/animations/hello.mp4", required = true)
    private String avatarAnimationUrl;

    @Schema(description = "Whether the translation is active", example = "true")
    private Boolean isActive;

    @Schema(description = "Creation timestamp", example = "2024-03-20T10:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Last update timestamp", example = "2024-03-20T10:30:00")
    private LocalDateTime updatedAt;
} 