package grupo05.inclusiveaid.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração de cache para a aplicação InclusiveAID.
 * Esta classe configura o sistema de cache utilizando o ConcurrentMapCacheManager
 * do Spring, que armazena os dados em memória usando mapas concorrentes.
 * 
 * O cache é utilizado para melhorar a performance da aplicação,
 * reduzindo a necessidade de acessos frequentes ao banco de dados
 * para dados que não mudam com frequência.
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * Configura o gerenciador de cache da aplicação.
     * Define três caches principais:
     * - "agents": para armazenar agentes de IA
     * - "users": para armazenar dados de usuários
     * - "adaptations": para armazenar configurações de adaptação
     * 
     * O ConcurrentMapCacheManager é thread-safe e adequado para
     * aplicações com múltiplas threads acessando o cache simultaneamente.
     * 
     * @return Gerenciador de cache configurado
     */
    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        cacheManager.setCacheNames(java.util.Arrays.asList("agents", "users", "adaptations"));
        return cacheManager;
    }
} 