package grupo05.inclusiveaid.config;

import jakarta.persistence.Embeddable;
import lombok.*;

/**
 * Configuração para processamento de voz na aplicação InclusiveAID.
 * Esta classe define os parâmetros utilizados no processamento e reconhecimento
 * de voz para acessibilidade, incluindo reconhecimento de fala e redução de ruído.
 * 
 * A classe é marcada como @Embeddable para permitir sua incorporação
 * em outras entidades JPA, facilitando o armazenamento das configurações
 * no banco de dados.
 */
@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VoiceProcessingConfig {
    /**
     * Nome ou caminho do modelo de reconhecimento de fala a ser utilizado.
     * Define qual modelo de IA será usado para transcrever áudio em texto.
     */
    private String speechRecognitionModel;

    /**
     * Limiar de confiança para reconhecimento de fala.
     * Define o nível mínimo de confiança (0.0 a 1.0) que uma transcrição
     * deve ter para ser considerada válida.
     */
    private double recognitionThreshold;

    /**
     * Flag que indica se a redução de ruído está habilitada
     * no processamento de áudio. A redução de ruído pode melhorar
     * a qualidade do reconhecimento de fala em ambientes ruidosos.
     */
    private boolean enableNoiseReduction;
} 