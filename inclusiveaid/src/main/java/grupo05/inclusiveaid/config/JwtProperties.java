package grupo05.inclusiveaid.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

/**
 * Propriedades de configuração para JWT (JSON Web Token) na aplicação InclusiveAID.
 * Esta classe define propriedades adicionais relacionadas aos tokens JWT,
 * complementando as configurações básicas definidas em JwtConfig.
 * 
 * As propriedades são carregadas automaticamente do arquivo de configuração
 * da aplicação (application.properties/yml) usando o prefixo "jwt".
 * 
 * @see JwtConfig
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    /**
     * Período de validade dos tokens JWT em milissegundos.
     * Define o tempo máximo que um token pode ser utilizado antes de expirar.
     * Este valor é utilizado para definir a data de expiração dos tokens
     * durante sua geração.
     */
    private long validity;
} 