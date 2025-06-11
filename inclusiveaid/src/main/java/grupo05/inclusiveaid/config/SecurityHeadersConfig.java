package grupo05.inclusiveaid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.header.HeaderWriter;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.header.writers.DelegatingRequestMatcherHeaderWriter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuração de cabeçalhos de segurança HTTP da aplicação InclusiveAID.
 * Esta classe define os cabeçalhos de segurança que serão adicionados às respostas HTTP,
 * ajudando a proteger a aplicação contra diversos tipos de ataques.
 * 
 * Implementa:
 * - Proteção contra MIME type sniffing
 * - Proteção contra clickjacking
 * - Proteção contra XSS
 * - Políticas de segurança de conteúdo (CSP) específicas para Swagger UI
 */
@Configuration
public class SecurityHeadersConfig {

    /**
     * Configura o filtro de cabeçalhos de segurança.
     * Define diferentes conjuntos de cabeçalhos de segurança para diferentes
     * partes da aplicação, incluindo cabeçalhos padrão e específicos para Swagger UI.
     * 
     * Cabeçalhos implementados:
     * - X-Content-Type-Options: nosniff
     * - X-Frame-Options: DENY
     * - X-XSS-Protection: 1; mode=block
     * - Content-Security-Policy: Configurações específicas para Swagger UI e API docs
     * 
     * @return Filtro configurado com todos os cabeçalhos de segurança
     */
    @Bean
    public HeaderWriterFilter headerWriterFilter() {
        List<HeaderWriter> writers = new ArrayList<>();

        // Default headers for all requests
        writers.add(new StaticHeadersWriter("X-Content-Type-Options", "nosniff"));
        writers.add(new StaticHeadersWriter("X-Frame-Options", "DENY"));
        writers.add(new StaticHeadersWriter("X-XSS-Protection", "1; mode=block"));

        // Swagger UI specific headers
        writers.add(new DelegatingRequestMatcherHeaderWriter(
            (RequestMatcher) request -> request.getRequestURI().startsWith("/swagger-ui/"),
            new StaticHeadersWriter("Content-Security-Policy", 
                "default-src 'self'; " +
                "script-src 'self' 'unsafe-inline' 'unsafe-eval'; " +
                "style-src 'self' 'unsafe-inline'; " +
                "img-src 'self' data:; " +
                "font-src 'self' data:; " +
                "connect-src 'self'"
            )
        ));

        writers.add(new DelegatingRequestMatcherHeaderWriter(
            (RequestMatcher) request -> request.getRequestURI().startsWith("/v3/api-docs/"),
            new StaticHeadersWriter("Content-Security-Policy", 
                "default-src 'self'; " +
                "script-src 'self' 'unsafe-inline' 'unsafe-eval'; " +
                "style-src 'self' 'unsafe-inline'; " +
                "img-src 'self' data:; " +
                "font-src 'self' data:; " +
                "connect-src 'self'"
            )
        ));

        return new HeaderWriterFilter(writers);
    }
} 