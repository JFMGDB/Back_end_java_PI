package grupo05.inclusiveaid.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Filtro de logging para a aplicação InclusiveAID.
 * Este filtro intercepta todas as requisições HTTP e registra informações
 * detalhadas sobre a requisição e resposta, incluindo:
 * - Método HTTP e URI
 * - Duração da requisição
 * - Corpo da requisição e resposta
 * 
 * O filtro utiliza wrappers para permitir múltiplas leituras do corpo
 * da requisição e resposta, mantendo a funcionalidade original intacta.
 */
@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {

    /**
     * Processa cada requisição HTTP, registrando informações detalhadas.
     * Este método:
     * 1. Envolve a requisição e resposta em wrappers para permitir múltiplas leituras
     * 2. Mede o tempo de processamento da requisição
     * 3. Registra informações básicas da requisição (método, URI, duração)
     * 4. Registra o corpo da requisição e resposta em nível de debug
     * 5. Garante que o corpo da resposta seja copiado de volta para a resposta original
     * 
     * @param request Requisição HTTP recebida
     * @param response Resposta HTTP a ser enviada
     * @param filterChain Cadeia de filtros a ser executada
     * @throws ServletException Se ocorrer um erro no processamento da requisição
     * @throws IOException Se ocorrer um erro de I/O
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long duration = System.currentTimeMillis() - startTime;

        String requestBody = new String(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
        String responseBody = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);

        log.info("Request: {} {} - Duration: {}ms", request.getMethod(), request.getRequestURI(), duration);
        log.debug("Request Body: {}", requestBody);
        log.debug("Response Body: {}", responseBody);

        responseWrapper.copyBodyToResponse();
    }
} 