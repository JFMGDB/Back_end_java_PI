package grupo05.inclusiveaid.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para configurações específicas de deficiência de um agente IA.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Configurações específicas de acessibilidade para um agente e tipo de deficiência")
public class DisabilitySpecificConfigDTO {

    public interface Create {}
    public interface Update {}

    @Schema(description = "Identificador único da configuração", example = "1")
    private Long id;

    @NotNull(groups = {Create.class, Update.class})
    @Schema(description = "ID do agente de IA", example = "5", required = true)
    private Long agentId;

    @NotNull(groups = {Create.class, Update.class})
    @Schema(description = "ID do tipo de deficiência", example = "3", required = true)
    private Long disabilityTypeId;

    @Schema(description = "Ativa leitor de tela", example = "true")
    private boolean enableScreenReader;
    @Schema(description = "Ativa alto contraste", example = "false")
    private boolean enableHighContrast;
    @Schema(description = "Ativa descrição de imagens", example = "true")
    private boolean enableImageDescription;
    @Schema(description = "Ativa legendas", example = "true")
    private boolean enableSubtitles;
    @Schema(description = "Ativa alertas visuais", example = "false")
    private boolean enableVisualAlerts;
    @Schema(description = "Ativa linguagem de sinais", example = "true")
    private boolean enableSignLanguage;
    @Schema(description = "Ativa comandos de voz", example = "true")
    private boolean enableVoiceCommands;
    @Schema(description = "Ativa reconhecimento de gestos", example = "false")
    private boolean enableGestureRecognition;
    @Schema(description = "Ativa automação de tarefas", example = "true")
    private boolean enableTaskAutomation;
    @Schema(description = "Ativa modo simplificado", example = "false")
    private boolean enableSimplifiedMode;
    @Schema(description = "Ativa feedback consistente", example = "true")
    private boolean enableConsistentFeedback;
    @Schema(description = "Ativa redução de estímulos", example = "false")
    private boolean enableReducedStimuli;
    @Schema(description = "Ativa guia passo a passo", example = "true")
    private boolean enableStepByStepGuide;
    @Schema(description = "Ativa sumarização de conteúdo", example = "false")
    private boolean enableContentSummarization;
    @Schema(description = "Ativa guias visuais", example = "true")
    private boolean enableVisualGuidance;

    @Schema(description = "JSON com configurações adicionais", example = "{\"fontSize\":18}")
    private String customSettings;
} 