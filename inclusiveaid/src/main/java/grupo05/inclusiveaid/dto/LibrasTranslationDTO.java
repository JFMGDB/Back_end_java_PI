package grupo05.inclusiveaid.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) para transferência de dados de tradução em Libras.
 * Utilizado para gerenciar traduções de texto para Libras, incluindo animações
 * de avatar para melhorar a acessibilidade para usuários surdos.
 * 
 * @author Grupo 05
 * @version 1.0
 */
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Schema(description = "Objeto de Transferência de Dados para operações de Tradução em Libras")
public class LibrasTranslationDTO {
    /**
     * Interface para validação na criação de uma nova tradução.
     */
    public interface Create {}
    
    /**
     * Interface para validação na atualização de uma tradução existente.
     */
    public interface Update {}

    @Schema(description = "Identificador único da tradução", example = "1")
    private Long id;

    @NotBlank(groups = {Create.class, Update.class}, message = "O texto original é obrigatório")
    @Size(min = 1, max = 1000, message = "O texto deve ter entre 1 e 1000 caracteres")
    @Schema(description = "Texto original a ser traduzido para Libras", example = "Olá, como você está?", required = true)
    private String originalText;

    @NotBlank(groups = {Create.class, Update.class}, message = "A URL da animação é obrigatória")
    @Schema(description = "URL da animação do avatar em Libras", example = "https://example.com/animations/hello.mp4", required = true)
    private String avatarAnimationUrl;

    @Schema(description = "Indica se a tradução está ativa", example = "true")
    private Boolean isActive;

    @Schema(description = "Data e hora de criação", example = "2024-03-20T10:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Data e hora da última atualização", example = "2024-03-20T10:30:00")
    private LocalDateTime updatedAt;
} 