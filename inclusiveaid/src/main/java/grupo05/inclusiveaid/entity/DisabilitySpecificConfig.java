package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Representa configurações específicas do agente de IA para cada tipo de deficiência.
 * Esta entidade permite personalizar o comportamento do agente de acordo com as necessidades
 * específicas de cada tipo de deficiência, oferecendo diferentes níveis de suporte e adaptação.
 * As configurações são organizadas por categoria de deficiência (visual, auditiva, motora, TEA e cognitiva).
 */
@Entity
@Table(name = "disability_specific_configs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DisabilitySpecificConfig {

    /**
     * Identificador único da configuração no sistema.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Agente de IA associado a esta configuração.
     * Cada agente pode ter múltiplas configurações para diferentes tipos de deficiência.
     */
    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private AIAgent agent;

    /**
     * Tipo de deficiência para o qual esta configuração se aplica.
     * Define o contexto específico das adaptações.
     */
    @ManyToOne
    @JoinColumn(name = "disability_type_id", nullable = false)
    private DisabilityType disabilityType;

    /**
     * Habilita o leitor de tela para usuários com deficiência visual.
     * Permite que o sistema leia o conteúdo em voz alta.
     */
    @Column
    private boolean enableScreenReader;
    
    /**
     * Habilita o modo de alto contraste para melhorar a visibilidade.
     * Aumenta o contraste entre elementos visuais.
     */
    @Column
    private boolean enableHighContrast;
    
    /**
     * Habilita descrições automáticas de imagens.
     * Fornece descrições detalhadas de elementos visuais.
     */
    @Column
    private boolean enableImageDescription;

    /**
     * Habilita legendas para conteúdo de áudio.
     * Fornece transcrição de diálogos e sons.
     */
    @Column
    private boolean enableSubtitles;
    
    /**
     * Habilita alertas visuais para notificações sonoras.
     * Converte sinais sonoros em alertas visuais.
     */
    @Column
    private boolean enableVisualAlerts;
    
    /**
     * Habilita suporte a linguagem de sinais.
     * Fornece tradução para LIBRAS.
     */
    @Column
    private boolean enableSignLanguage;

    /**
     * Habilita comandos de voz para controle do sistema.
     * Permite interação por voz para usuários com dificuldades motoras.
     */
    @Column
    private boolean enableVoiceCommands;
    
    /**
     * Habilita reconhecimento de gestos para interação.
     * Permite controle do sistema através de movimentos.
     */
    @Column
    private boolean enableGestureRecognition;
    
    /**
     * Habilita automação de tarefas repetitivas.
     * Reduz a necessidade de interação manual.
     */
    @Column
    private boolean enableTaskAutomation;

    /**
     * Habilita modo simplificado da interface.
     * Reduz a complexidade visual e cognitiva.
     */
    @Column
    private boolean enableSimplifiedMode;
    
    /**
     * Habilita feedback consistente e previsível.
     * Ajuda na compreensão das ações do sistema.
     */
    @Column
    private boolean enableConsistentFeedback;
    
    /**
     * Habilita redução de estímulos visuais e sonoros.
     * Minimiza distrações e sobrecarga sensorial.
     */
    @Column
    private boolean enableReducedStimuli;

    /**
     * Habilita guia passo a passo para tarefas.
     * Fornece instruções detalhadas e sequenciais.
     */
    @Column
    private boolean enableStepByStepGuide;
    
    /**
     * Habilita resumo de conteúdo para melhor compreensão.
     * Simplifica informações complexas.
     */
    @Column
    private boolean enableContentSummarization;
    
    /**
     * Habilita guias visuais para navegação.
     * Fornece pistas visuais para orientação.
     */
    @Column
    private boolean enableVisualGuidance;

    /**
     * Configurações personalizadas adicionais em formato JSON.
     * Permite extensibilidade para necessidades específicas não cobertas pelos campos padrão.
     */
    @Column
    private String customSettings;
} 