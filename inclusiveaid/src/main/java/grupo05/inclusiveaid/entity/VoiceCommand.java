package grupo05.inclusiveaid.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

/**
 * Entidade que representa um comando de voz no sistema InclusiveAID.
 * Esta entidade registra os comandos de voz reconhecidos pelo sistema,
 * permitindo interação por voz para usuários com dificuldades motoras
 * ou preferência por comandos de voz.
 */
@Entity
@Table(name = "voice_commands")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VoiceCommand {
  /**
   * Identificador único do comando de voz no sistema.
   * Gerado automaticamente pelo banco de dados.
   */
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Sessão à qual o comando de voz está associado.
   * Permite rastrear comandos dentro de uma sessão específica de uso.
   */
  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="session_id")
  private Session session;

  /**
   * Texto do comando de voz reconhecido.
   * Contém a transcrição do comando falado pelo usuário.
   */
  private String command;

  /**
   * Resultado da execução do comando.
   * Armazena a resposta ou ação realizada pelo sistema.
   */
  private String result;

  /**
   * Momento em que o comando foi reconhecido e processado.
   * Utilizado para ordenação e análise temporal dos comandos.
   */
  private Instant timestamp;
}
