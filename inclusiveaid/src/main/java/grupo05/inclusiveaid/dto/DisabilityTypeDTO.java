package grupo05.inclusiveaid.dto;
import lombok.*;
import jakarta.validation.constraints.*;

/**
 * DTO (Data Transfer Object) para tipos de deficiência.
 * Utilizado para transferir dados relacionados aos diferentes tipos de deficiência
 * suportados pelo sistema, permitindo a personalização das funcionalidades de acessibilidade.
 * 
 * @author Grupo 05
 * @version 1.0
 */
@Data @Builder
public class DisabilityTypeDTO {
    /**
     * Interface para validação na criação de um novo tipo de deficiência.
     */
    public interface Create {}
    
    /**
     * Interface para validação na atualização de um tipo de deficiência existente.
     */
    public interface Update {}

    /**
     * Identificador único do tipo de deficiência.
     */
    private Long id;

    /**
     * Nome do tipo de deficiência.
     * Não pode estar em branco.
     */
    @NotBlank(groups = {Create.class, Update.class})
    private String name;

    /**
     * Descrição detalhada do tipo de deficiência.
     * Não pode estar em branco.
     */
    @NotBlank(groups = {Create.class, Update.class})
    private String description;
}