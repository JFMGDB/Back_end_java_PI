package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO (Data Transfer Object) para agentes de IA.
 * Utilizado para transferir dados relacionados aos agentes de inteligência artificial
 * que auxiliam na acessibilidade do sistema.
 * 
 * @author Grupo 05
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AIAgentDTO {
    /**
     * Identificador único do agente de IA.
     */
    private Long id;

    /**
     * Nome do agente de IA.
     * Não pode estar em branco.
     */
    @NotBlank
    private String name;

    /**
     * Versão do agente de IA.
     * Não pode estar em branco.
     */
    @NotBlank
    private String version;

    /**
     * Indica se o agente está ativo no sistema.
     */
    private boolean isActive;

    /**
     * Data e hora da última atualização do agente.
     */
    private LocalDateTime lastUpdate;
    
    /**
     * Configurações de Processamento de Linguagem Natural (NLP).
     * Não pode ser nulo.
     */
    @NotNull
    private NLPConfigDTO nlpConfig;

    /**
     * Configurações de Processamento de Imagem.
     * Não pode ser nulo.
     */
    @NotNull
    private ImageProcessingConfigDTO imageConfig;

    /**
     * Configurações de Processamento de Voz.
     * Não pode ser nulo.
     */
    @NotNull
    private VoiceProcessingConfigDTO voiceConfig;
    
    /**
     * Conjunto de identificadores dos usuários ativos associados ao agente.
     */
    private Set<Long> activeUserIds;

    /**
     * Conjunto de identificadores das configurações de deficiência associadas ao agente.
     */
    private Set<Long> disabilityConfigIds;
} 