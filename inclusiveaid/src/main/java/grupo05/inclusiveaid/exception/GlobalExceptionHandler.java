package grupo05.inclusiveaid.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiException> handleNotFound(ResourceNotFoundException ex) {
    return new ResponseEntity<>(
      new ApiException(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
      HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiException> handleAll(Exception ex) {
    return new ResponseEntity<>(
      new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno"),
      HttpStatus.INTERNAL_SERVER_ERROR);
  }
}