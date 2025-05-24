package grupo05.inclusiveaid.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configurações do Swagger/OpenAPI,
 * incluindo esquema JWT Bearer global.
 */
@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI apiInfo() {
    return new OpenAPI()
      .components(new Components()
        .addSecuritySchemes("BearerAuth",
          new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .in(SecurityScheme.In.HEADER)
            .name("Authorization")
        )
      )
      .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
      .info(new Info()
        .title("InclusiveAID Backend API")
        .version("1.0")
        .description("API para Assistente de Inclusão Digital – autenticação JWT suportada")
      );
  }
}