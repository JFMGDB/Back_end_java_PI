package grupo05.inclusiveaid.exception;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiException {
    private int status;
    private String message;
    private String errorCode;
    private LocalDateTime timestamp;
}