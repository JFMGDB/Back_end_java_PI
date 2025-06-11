package grupo05.inclusiveaid.entity;

import grupo05.inclusiveaid.config.NLPConfig;
import grupo05.inclusiveaid.config.ImageProcessingConfig;
import grupo05.inclusiveaid.config.VoiceProcessingConfig;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Representa o agente de IA do sistema AID.
 * O agente é responsável por:
 * - Analisar layouts e interações do usuário
 * - Fornecer adaptações em tempo real
 * - Gerenciar diferentes modos de assistência
 * - Processar comandos de voz e gestos
 * - Gerar legendas e descrições
 */
@Entity
@Table(name = "ai_agents")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AIAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private LocalDateTime lastUpdate;

    // Configurações de processamento de linguagem natural
    @Embedded
    private NLPConfig nlpConfig;

    // Configurações de processamento de imagem
    @Embedded
    private ImageProcessingConfig imageConfig;

    // Configurações de processamento de voz
    @Embedded
    private VoiceProcessingConfig voiceConfig;

    // Relação com usuários que o agente está assistindo
    @ManyToMany
    @JoinTable(
        name = "agent_active_users",
        joinColumns = @JoinColumn(name = "agent_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Builder.Default
    private Set<User> activeUsers = new HashSet<>();

    // Histórico de interações do agente
    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    @Builder.Default
    private List<AgentInteraction> interactions = new ArrayList<>();

    // Configurações específicas para cada tipo de deficiência
    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    @Builder.Default
    private List<DisabilitySpecificConfig> disabilityConfigs = new ArrayList<>();

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastUpdate = LocalDateTime.now();
    }
} 