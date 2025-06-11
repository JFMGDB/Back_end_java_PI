package grupo05.inclusiveaid.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsibleDTO {
    private Long id;

    @NotBlank(groups = {Create.class, Update.class})
    private String name;

    @NotBlank(groups = {Create.class, Update.class})
    @Email(groups = {Create.class, Update.class})
    private String email;

    @JsonIgnore
    @NotBlank(groups = {Create.class})
    private String password;

    @NotBlank(groups = {Create.class, Update.class})
    private String phone;

    @NotNull(groups = {Create.class, Update.class})
    private Long userId;

    public interface Create {}
    public interface Update {}
} 