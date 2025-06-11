package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa um elemento de layout no sistema InclusiveAID.
 * Esta entidade armazena informações sobre elementos da interface do usuário,
 * permitindo sua identificação e análise para adaptações de acessibilidade.
 * Os elementos são identificados por seu tipo, descrição e localização no DOM.
 */
@Entity
@Table(name = "layout_elements")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LayoutElement {
  /**
   * Identificador único do elemento no sistema.
   * Gerado automaticamente pelo banco de dados.
   */
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Tipo do elemento de interface.
   * Identifica a categoria do elemento (ex: botão, campo de texto, menu).
   */
  private String elementType;

  /**
   * Descrição do elemento.
   * Fornece informações sobre o propósito e função do elemento
   * para auxiliar na adaptação de acessibilidade.
   */
  private String description;

  /**
   * Localização do elemento no DOM.
   * Utiliza XPath para identificar precisamente a posição
   * do elemento na estrutura da página.
   */
  private String xpath;
}