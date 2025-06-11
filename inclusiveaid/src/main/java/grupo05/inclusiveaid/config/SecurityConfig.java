package grupo05.inclusiveaid.config;

import grupo05.inclusiveaid.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuração de segurança da aplicação InclusiveAID.
 * Esta classe define as regras de segurança, autenticação e autorização do sistema.
 * Implementa:
 * - Autenticação baseada em JWT (JSON Web Token)
 * - Configuração de endpoints públicos e protegidos
 * - Políticas de segurança de conteúdo (CSP)
 * - Gerenciamento de sessão stateless
 * - Proteção contra CSRF
 * 
 * A configuração permite acesso público a:
 * - Documentação da API (Swagger)
 * - Endpoints de autenticação
 * - Console H2 (em ambiente de desenvolvimento)
 * - Páginas de erro
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;

    /**
     * Configura a cadeia de filtros de segurança da aplicação.
     * Define as regras de acesso para diferentes endpoints e configura
     * as políticas de segurança.
     * 
     * Configurações implementadas:
     * - Desabilita proteção CSRF para API REST
     * - Define endpoints públicos e protegidos
     * - Configura gerenciamento de sessão como stateless
     * - Define políticas de segurança de conteúdo
     * - Adiciona filtro JWT para autenticação
     * 
     * @param http Configuração de segurança HTTP do Spring Security
     * @return Cadeia de filtros de segurança configurada
     * @throws Exception Se houver erro na configuração
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/v3/api-docs/**",
                    "/v3/api-docs.yaml",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/swagger-resources/**",
                    "/webjars/**",
                    "/api/auth/**",
                    "/h2-console/**",
                    "/error"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.disable())
                .contentSecurityPolicy(csp -> csp
                    .policyDirectives("default-src 'self'; " +
                        "script-src 'self' 'unsafe-inline' 'unsafe-eval'; " +
                        "style-src 'self' 'unsafe-inline'; " +
                        "img-src 'self' data:; " +
                        "frame-ancestors 'self'")
                )
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
