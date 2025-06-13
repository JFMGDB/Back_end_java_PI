package grupo05.inclusiveaid.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Manipulador global de exceções da aplicação InclusiveAID.
 * Esta classe centraliza o tratamento de todas as exceções lançadas pela aplicação,
 * padronizando as respostas de erro e garantindo uma experiência consistente
 * para os usuários. Implementa diferentes handlers para cada tipo de exceção,
 * retornando respostas HTTP apropriadas com mensagens de erro claras.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Manipula exceções de recursos não encontrados.
     * Retorna uma resposta 404 (Not Found) quando um recurso solicitado não existe.
     * @param ex Exceção lançada quando um recurso não é encontrado
     * @return Resposta HTTP 404 com detalhes do erro
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Manipula exceções de validação de dados.
     * Retorna uma resposta 400 (Bad Request) quando os dados enviados são inválidos.
     * @param ex Exceção lançada quando a validação falha
     * @return Resposta HTTP 400 com detalhes do erro
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manipula exceções de não autorização.
     * Retorna uma resposta 401 (Unauthorized) quando o usuário não está autenticado.
     * @param ex Exceção lançada quando o usuário não está autorizado
     * @return Resposta HTTP 401 com detalhes do erro
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.UNAUTHORIZED.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Manipula exceções de email duplicado.
     * Retorna uma resposta 409 (Conflict) quando um email já está em uso.
     * @param ex Exceção lançada quando um email já existe
     * @return Resposta HTTP 409 com detalhes do erro
     */
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEmailException(DuplicateEmailException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.CONFLICT.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /**
     * Manipula exceções de acesso negado.
     * Retorna uma resposta 403 (Forbidden) quando o usuário não tem permissão.
     * @param ex Exceção lançada quando o acesso é negado
     * @return Resposta HTTP 403 com detalhes do erro
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.FORBIDDEN.value(),
            "Access denied",
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    /**
     * Manipula exceções de validação de argumentos de método.
     * Retorna uma resposta 400 (Bad Request) com detalhes dos campos inválidos.
     * @param ex Exceção lançada quando os argumentos do método são inválidos
     * @return Resposta HTTP 400 com mapa de erros por campo
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manipula exceções de violação de restrições.
     * Retorna uma resposta 400 (Bad Request) com detalhes das violações.
     * @param ex Exceção lançada quando há violação de restrições
     * @return Resposta HTTP 400 com mapa de erros por campo
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manipula exceções de incompatibilidade de tipo de argumento.
     * Retorna uma resposta 400 (Bad Request) com detalhes do erro de tipo.
     * @param ex Exceção lançada quando o tipo do argumento é incompatível
     * @return Resposta HTTP 400 com detalhes do erro
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiException> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Class<?> requiredType = ex.getRequiredType();
        String typeName = requiredType != null ? requiredType.getSimpleName() : "unknown";
        String errorMessage = String.format("Parameter '%s' must be of type %s", 
            ex.getName(), typeName);
        ApiException error = ApiException.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(errorMessage)
                .errorCode("TYPE_MISMATCH")
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manipula exceções de parâmetros de requisição ausentes.
     * Retorna uma resposta 400 (Bad Request) com detalhes do parâmetro faltante.
     * @param ex Exceção lançada quando um parâmetro obrigatório está ausente
     * @return Resposta HTTP 400 com detalhes do erro
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiException> handleMissingParams(MissingServletRequestParameterException ex) {
        String errorMessage = String.format("Required parameter '%s' is missing", ex.getParameterName());
        ApiException error = ApiException.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(errorMessage)
                .errorCode("MISSING_PARAMETER")
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manipula exceções de tamanho máximo de upload excedido.
     * Retorna uma resposta 413 (Payload Too Large) quando o arquivo é muito grande.
     * @param ex Exceção lançada quando o tamanho do arquivo excede o limite
     * @return Resposta HTTP 413 com detalhes do erro
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiException> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        ApiException error = ApiException.builder()
                .status(HttpStatus.PAYLOAD_TOO_LARGE.value())
                .message("File exceeds maximum allowed size")
                .errorCode("FILE_TOO_LARGE")
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(error, HttpStatus.PAYLOAD_TOO_LARGE);
    }

    /**
     * Manipula exceções de credenciais inválidas.
     * Retorna uma resposta 401 (Unauthorized) quando as credenciais são inválidas.
     * @param ex Exceção lançada quando as credenciais são inválidas
     * @return Resposta HTTP 401 com detalhes do erro
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.UNAUTHORIZED.value(),
            "Invalid credentials",
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Manipula exceções genéricas não tratadas.
     * Retorna uma resposta 500 (Internal Server Error) para erros não mapeados.
     * @param ex Exceção genérica não tratada
     * @return Resposta HTTP 500 com mensagem genérica de erro
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        log.error("Unhandled exception", ex);
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "An unexpected error occurred",
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

/**
 * Classe que representa a estrutura padrão de resposta de erro.
 * Contém informações sobre o status, mensagem e timestamp do erro.
 */
class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    /**
     * Construtor da resposta de erro.
     * @param status Código HTTP do erro
     * @param message Mensagem descritiva do erro
     * @param timestamp Momento em que o erro ocorreu
     */
    public ErrorResponse(int status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}