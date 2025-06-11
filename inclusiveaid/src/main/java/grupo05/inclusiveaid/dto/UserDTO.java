package grupo05.inclusiveaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.*;

/**
 * DTO (Data Transfer Object) para transferência de dados de usuário.
 * Utilizado para transferir dados entre a camada de apresentação e a camada de serviço.
 */
@Data @Builder
@Schema(description = "Data Transfer Object for User operations")
public class UserDTO {
  public interface Create {}
  public interface Update {}

  @Schema(description = "Unique identifier of the user", example = "1")
  private Long id;

  @NotBlank(groups = {Create.class, Update.class}, message = "O nome é obrigatório")
  @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
  @Schema(description = "Full name of the user", example = "John Doe", required = true)
  private String name;

  @NotBlank(groups = {Create.class, Update.class}, message = "O email é obrigatório")
  @Email(groups = {Create.class, Update.class}, message = "Email inválido")
  @Schema(description = "Email address of the user", example = "john.doe@example.com", required = true)
  private String email;

  // Obrigatório apenas na criação
  @NotBlank(groups = Create.class, message = "A senha é obrigatória")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @NotNull(groups = {Create.class, Update.class})
  private Long roleId;

  // IDs dos tipos de deficiência associados ao usuário
  private Set<Long> disabilityTypeIds;

  @Schema(description = "Type of disability of the user", example = "VISUAL")
  private String disabilityType;

  @Schema(description = "Additional notes about the user's needs", example = "Requires high contrast mode")
  private String notes;

  @Schema(description = "Whether the user account is active", example = "true")
  private Boolean active;
}