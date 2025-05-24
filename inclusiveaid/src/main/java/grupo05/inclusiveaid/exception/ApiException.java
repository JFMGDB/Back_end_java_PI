package grupo05.inclusiveaid.exception;

import lombok.*;
@Data @AllArgsConstructor
public class ApiException {
  private int status;
  private String message;
}