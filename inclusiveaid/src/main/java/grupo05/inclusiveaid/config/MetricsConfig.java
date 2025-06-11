package grupo05.inclusiveaid.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração de métricas para a aplicação InclusiveAID.
 * Esta classe configura o sistema de métricas utilizando Micrometer,
 * permitindo o monitoramento de performance e comportamento da aplicação.
 * 
 * O TimedAspect permite a medição automática do tempo de execução
 * de métodos anotados com @Timed, facilitando a coleta de métricas
 * de performance.
 */
@Configuration
public class MetricsConfig {

    /**
     * Cria um aspecto para medição automática de tempo de execução.
     * Este bean permite a coleta de métricas de tempo de execução
     * para métodos anotados com @Timed, que podem ser visualizados
     * em sistemas de monitoramento como Prometheus ou Grafana.
     * 
     * @param registry Registro de métricas do Micrometer
     * @return Aspecto configurado para medição de tempo
     */
    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
} 