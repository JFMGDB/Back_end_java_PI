package grupo05.inclusiveaid.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configurações do Swagger/OpenAPI,
 * incluindo esquema JWT Bearer global.
 */
@Configuration
public class OpenApiConfig {

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
      .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
      .components(new io.swagger.v3.oas.models.Components()
        .addSecuritySchemes(securitySchemeName,
          new SecurityScheme()
            .name(securitySchemeName)
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .description("JWT token de autenticação")));
  }
}