package grupo05.inclusiveaid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Configuração web da aplicação InclusiveAID.
 * Esta classe configura aspectos gerais da aplicação web, incluindo:
 * - Configuração CORS (Cross-Origin Resource Sharing)
 * - Bean para RestTemplate para chamadas HTTP
 * - Bean para ObjectMapper para serialização/desserialização JSON
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    /**
     * Configura as regras de CORS para a aplicação.
     * Define quais origens, métodos e headers são permitidos nas requisições cross-origin.
     * 
     * Configurações implementadas:
     * - Origens permitidas: localhost:3000 e localhost:8080
     * - Métodos HTTP permitidos: GET, POST, PUT, DELETE, OPTIONS, PATCH
     * - Todos os headers são permitidos
     * - Header de autorização é exposto
     * - Credenciais são permitidas
     * - Cache de preflight por 1 hora
     * 
     * @param registry Registro de configuração CORS
     */
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * Cria um bean RestTemplate para realizar chamadas HTTP.
     * O RestTemplate é utilizado para comunicação com serviços externos
     * e APIs de terceiros.
     * 
     * @return Instância configurada de RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Cria um bean ObjectMapper para manipulação de JSON.
     * O ObjectMapper é utilizado para serialização e desserialização
     * de objetos Java para/do formato JSON.
     * 
     * @return Instância configurada de ObjectMapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
