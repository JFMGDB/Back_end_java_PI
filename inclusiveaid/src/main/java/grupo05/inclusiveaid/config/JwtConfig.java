package grupo05.inclusiveaid.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

/**
 * Configuração para JWT (JSON Web Token) na aplicação InclusiveAID.
 * Esta classe define as propriedades necessárias para a geração e validação
 * de tokens JWT, que são utilizados para autenticação e autorização.
 * 
 * As propriedades são carregadas automaticamente do arquivo de configuração
 * da aplicação (application.properties/yml) usando o prefixo "jwt".
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    /**
     * Chave secreta utilizada para assinar e verificar tokens JWT.
     * Esta chave deve ser mantida em segredo e ter um tamanho adequado
     * para garantir a segurança dos tokens.
     */
    private String secret;

    /**
     * Tempo de expiração dos tokens JWT em milissegundos.
     * Define por quanto tempo um token será válido após sua emissão.
     * Após este período, o token expira e o usuário precisa se autenticar novamente.
     */
    private long expiration;
} 