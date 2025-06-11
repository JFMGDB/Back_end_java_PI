package grupo05.inclusiveaid.config;

import jakarta.persistence.Embeddable;
import lombok.*;

/**
 * Configuração para processamento de imagens na aplicação InclusiveAID.
 * Esta classe define os parâmetros utilizados no processamento e análise
 * de imagens para acessibilidade, incluindo detecção de objetos e OCR.
 * 
 * A classe é marcada como @Embeddable para permitir sua incorporação
 * em outras entidades JPA, facilitando o armazenamento das configurações
 * no banco de dados.
 */
@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ImageProcessingConfig {
    /**
     * Nome ou caminho do modelo de detecção de objetos a ser utilizado.
     * Define qual modelo de IA será usado para identificar objetos nas imagens.
     */
    private String objectDetectionModel;

    /**
     * Limiar de confiança para detecção de objetos.
     * Define o nível mínimo de confiança (0.0 a 1.0) que uma detecção
     * deve ter para ser considerada válida.
     */
    private double detectionThreshold;

    /**
     * Flag que indica se o OCR (Reconhecimento Óptico de Caracteres)
     * está habilitado para processamento de texto em imagens.
     */
    private boolean enableOCR;
} 