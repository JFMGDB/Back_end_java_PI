package grupo05.inclusiveaid.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * Configuração do Circuit Breaker para serviços de IA na aplicação InclusiveAID.
 * Esta classe implementa o padrão Circuit Breaker para proteger a aplicação
 * contra falhas em serviços de IA externos, melhorando a resiliência do sistema.
 * 
 * O Circuit Breaker monitora as chamadas aos serviços de IA e:
 * - Abre o circuito quando a taxa de falha excede o limiar
 * - Permite um número limitado de chamadas em estado semi-aberto
 * - Fecha o circuito após um período de espera
 */
@Configuration
public class AICircuitBreakerConfig {

    /**
     * Cria um Circuit Breaker para os serviços de IA.
     * Configura o comportamento do Circuit Breaker com os seguintes parâmetros:
     * - Janela deslizante baseada em contagem (últimas 10 chamadas)
     * - Limiar de taxa de falha de 50%
     * - Tempo de espera de 60 segundos no estado aberto
     * - 5 chamadas permitidas no estado semi-aberto
     * 
     * Estados do Circuit Breaker:
     * - Fechado: operação normal
     * - Aberto: bloqueia chamadas após muitas falhas
     * - Semi-aberto: permite algumas chamadas para testar recuperação
     * 
     * @return Circuit Breaker configurado para serviços de IA
     */
    @Bean
    public CircuitBreaker aiServiceCircuitBreaker() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .slidingWindowType(SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(10)
                .failureRateThreshold(50.0f)
                .waitDurationInOpenState(Duration.ofSeconds(60))
                .permittedNumberOfCallsInHalfOpenState(5)
                .build();

        return CircuitBreaker.of("aiService", config);
    }
} 