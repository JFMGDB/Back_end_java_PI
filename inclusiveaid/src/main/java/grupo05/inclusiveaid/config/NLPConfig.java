package grupo05.inclusiveaid.config;

import jakarta.persistence.Embeddable;
import lombok.*;

/**
 * Configuração para Processamento de Linguagem Natural (NLP) na aplicação InclusiveAID.
 * Esta classe define os parâmetros utilizados no processamento e análise
 * de texto e linguagem natural, incluindo modelos de linguagem e limiares de confiança.
 * 
 * A classe é marcada como @Embeddable para permitir sua incorporação
 * em outras entidades JPA, facilitando o armazenamento das configurações
 * no banco de dados.
 */
@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NLPConfig {
    /**
     * Nome ou caminho do modelo de linguagem a ser utilizado.
     * Define qual modelo de IA será usado para processamento de linguagem natural,
     * como análise de sentimento, classificação de texto ou geração de texto.
     */
    private String languageModel;

    /**
     * Limiar de confiança para processamento de linguagem natural.
     * Define o nível mínimo de confiança (0.0 a 1.0) que uma análise
     * deve ter para ser considerada válida.
     */
    private double confidenceThreshold;

    /**
     * Flag que indica se a consciência contextual está habilitada.
     * Quando ativada, o sistema considera o contexto das interações
     * anteriores para melhorar a compreensão e resposta.
     */
    private boolean enableContextAwareness;
} 