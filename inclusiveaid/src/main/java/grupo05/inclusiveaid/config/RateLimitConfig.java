package grupo05.inclusiveaid.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * Configuração de limitação de taxa (Rate Limiting) para a aplicação InclusiveAID.
 * Esta classe implementa o controle de taxa de requisições para proteger
 * a API contra sobrecarga e ataques de força bruta.
 * 
 * Utiliza a biblioteca Bucket4j para implementar o algoritmo Token Bucket,
 * que permite um número limitado de requisições em um determinado período.
 */
@Configuration
public class RateLimitConfig {

    /**
     * Cria um novo bucket para controle de taxa de requisições.
     * Configura um limite de 100 requisições por minuto para cada cliente.
     * 
     * O algoritmo Token Bucket funciona da seguinte forma:
     * - Cada bucket tem uma capacidade máxima de tokens
     * - Tokens são adicionados a uma taxa constante
     * - Cada requisição consome um token
     * - Requisições são rejeitadas quando não há tokens disponíveis
     * 
     * @return Bucket configurado para controle de taxa
     */
    @Bean
    public Bucket createNewBucket() {
        Bandwidth limit = Bandwidth.simple(100, Duration.ofMinutes(1));
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }
} 