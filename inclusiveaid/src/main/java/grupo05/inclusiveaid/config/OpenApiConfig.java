package grupo05.inclusiveaid.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
  @Bean
  public OpenAPI apiInfo() {
    return new OpenAPI()
      .info(new Info()
        .title("InclusiveAID Backend API")
        .version("1.0")
        .description("API para Assistente de Inclusão Digital"));
  }
}
