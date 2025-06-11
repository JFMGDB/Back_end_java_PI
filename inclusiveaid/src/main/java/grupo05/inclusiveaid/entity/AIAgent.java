package grupo05.inclusiveaid.entity;

import grupo05.inclusiveaid.config.NLPConfig;
import grupo05.inclusiveaid.config.ImageProcessingConfig;
import grupo05.inclusiveaid.config.VoiceProcessingConfig;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Representa o agente de IA do sistema InclusiveAID.
 * Esta entidade é responsável por gerenciar e coordenar as funcionalidades de inteligência artificial
 * que auxiliam usuários com diferentes tipos de deficiência. O agente:
 * - Analisa layouts e interações do usuário para identificar padrões de uso
 * - Fornece adaptações em tempo real baseadas nas necessidades do usuário
 * - Gerencia diferentes modos de assistência (visual, auditiva, motora)
 * - Processa comandos de voz e gestos para interação natural
 * - Gera legendas e descrições para conteúdo multimídia
 */
@Entity
@Table(name = "ai_agents")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AIAgent {

    /**
     * Identificador único do agente no sistema.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do agente de IA.
     * Identifica a instância específica do agente no sistema.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Versão atual do agente.
     * Utilizada para controle de versão e atualizações do sistema.
     */
    @Column(nullable = false)
    private String version;

    /**
     * Indica se o agente está ativo e disponível para uso.
     * Permite desativar temporariamente um agente para manutenção.
     */
    @Column(nullable = false)
    private boolean isActive;

    /**
     * Data e hora da última atualização do agente.
     * Atualizado automaticamente antes de cada persistência.
     */
    @Column(nullable = false)
    private LocalDateTime lastUpdate;

    /**
     * Configurações de processamento de linguagem natural.
     * Define parâmetros para análise de texto e compreensão de linguagem.
     */
    @Embedded
    private NLPConfig nlpConfig;

    /**
     * Configurações de processamento de imagem.
     * Define parâmetros para análise visual e reconhecimento de elementos.
     */
    @Embedded
    private ImageProcessingConfig imageConfig;

    /**
     * Configurações de processamento de voz.
     * Define parâmetros para reconhecimento e síntese de voz.
     */
    @Embedded
    private VoiceProcessingConfig voiceConfig;

    /**
     * Conjunto de usuários que o agente está atualmente assistindo.
     * Relação muitos-para-muitos que permite que um agente atenda múltiplos usuários simultaneamente.
     */
    @ManyToMany
    @JoinTable(
        name = "agent_active_users",
        joinColumns = @JoinColumn(name = "agent_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Builder.Default
    private Set<User> activeUsers = new HashSet<>();

    /**
     * Histórico de interações realizadas pelo agente.
     * Mantém um registro de todas as ações e respostas do agente para análise e melhorias.
     */
    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    @Builder.Default
    private List<AgentInteraction> interactions = new ArrayList<>();

    /**
     * Configurações específicas para cada tipo de deficiência.
     * Permite personalizar o comportamento do agente para diferentes necessidades.
     */
    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    @Builder.Default
    private List<DisabilitySpecificConfig> disabilityConfigs = new ArrayList<>();

    /**
     * Atualiza automaticamente o timestamp de última atualização.
     * Executado antes de cada operação de persistência ou atualização.
     */
    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastUpdate = LocalDateTime.now();
    }
} 