package grupo05.inclusiveaid.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuração da documentação OpenAPI (Swagger) da aplicação InclusiveAID.
 * Esta classe define a configuração da documentação da API, incluindo:
 * - Informações gerais da API
 * - Detalhes de contato
 * - Licença
 * - Servidores disponíveis
 * - Configuração de segurança opcional com JWT
 * 
 * A documentação é acessível através do Swagger UI em /swagger-ui/index.html
 */
@Configuration
public class OpenApiConfig {

    /**
     * Cria a configuração personalizada do OpenAPI.
     * Define todas as informações necessárias para a documentação da API,
     * incluindo metadados, servidores e esquema de segurança opcional.
     * 
     * Configurações implementadas:
     * - Título e descrição da API
     * - Versão atual
     * - Informações de contato
     * - Licença MIT
     * - Servidor de desenvolvimento local
     * - Esquema de segurança JWT Bearer (opcional)
     * 
     * @return Configuração completa do OpenAPI
     */
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        
        return new OpenAPI()
            .info(new Info()
                .title("Assistente de Inclusão Digital (AID) API")
                .description("""
                    API do Assistente de Inclusão Digital (AID), um software sobreposto que utiliza 
                    inteligência artificial para identificar, adaptar e melhorar a interação de 
                    Pessoas com Deficiência (PCDs) com aplicativos e sistemas.
                    
                    O AID analisa o layout e as ações do usuário, fornece feedback adequado e 
                    personaliza a experiência com base no tipo de deficiência (visual, auditiva, 
                    motora, cognitiva ou sensorial).
                    
                    Nota: Para desenvolvimento, alguns endpoints podem não exigir autenticação.
                    """)
                .version("1.0.0")
                .contact(new Contact()
                    .name("Grupo 05")
                    .email("grupo05@example.com"))
                .license(new License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT")))
            .servers(List.of(
                new Server()
                    .url("http://localhost:8080")
                    .description("Local Development Server")
            ))
            // Segurança JWT opcional - não obrigatória para todos os endpoints
            .components(new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes(securitySchemeName,
                    new SecurityScheme()
                        .name(securitySchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .description("JWT token de autenticação (opcional para alguns endpoints)")));
    }
}