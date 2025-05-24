package grupo05.inclusiveaid.dto;

import lombok.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.*;

/**
 * DTO para transferência de dados de User.
 * Usa grupos Create e Update para validações condicionais.
 */
@Data @Builder
public class UserDTO {
  public interface Create {}
  public interface Update {}

  private Long id;

  @NotBlank(groups = {Create.class, Update.class})
  private String name;

  @Email(groups = {Create.class, Update.class})
  @NotBlank(groups = {Create.class, Update.class})
  private String email;

  // Obrigatório apenas na criação
  @NotBlank(groups = Create.class)
  private String password;

  @NotNull(groups = {Create.class, Update.class})
  private Long roleId;

  // IDs dos tipos de deficiência associados ao usuário
  private Set<Long> disabilityTypeIds;
}