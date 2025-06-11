package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Representa configurações específicas do agente de IA para cada tipo de deficiência.
 * Permite personalizar o comportamento do agente de acordo com as necessidades do usuário.
 */
@Entity
@Table(name = "disability_specific_configs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DisabilitySpecificConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private AIAgent agent;

    @ManyToOne
    @JoinColumn(name = "disability_type_id", nullable = false)
    private DisabilityType disabilityType;

    // Configurações para deficiência visual
    @Column
    private boolean enableScreenReader;
    
    @Column
    private boolean enableHighContrast;
    
    @Column
    private boolean enableImageDescription;

    // Configurações para deficiência auditiva
    @Column
    private boolean enableSubtitles;
    
    @Column
    private boolean enableVisualAlerts;
    
    @Column
    private boolean enableSignLanguage;

    // Configurações para deficiência motora
    @Column
    private boolean enableVoiceCommands;
    
    @Column
    private boolean enableGestureRecognition;
    
    @Column
    private boolean enableTaskAutomation;

    // Configurações para TEA
    @Column
    private boolean enableSimplifiedMode;
    
    @Column
    private boolean enableConsistentFeedback;
    
    @Column
    private boolean enableReducedStimuli;

    // Configurações para deficiência cognitiva
    @Column
    private boolean enableStepByStepGuide;
    
    @Column
    private boolean enableContentSummarization;
    
    @Column
    private boolean enableVisualGuidance;

    @Column
    private String customSettings;
} 