package grupo05.inclusiveaid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação InclusiveAID.
 * Esta classe é o ponto de entrada da aplicação Spring Boot.
 * A anotação @SpringBootApplication configura automaticamente o Spring Boot,
 * incluindo a configuração automática, varredura de componentes e configuração do servidor web.
 */
@SpringBootApplication
public class InclusiveaidApplication {

	/**
	 * Método principal que inicia a aplicação Spring Boot.
	 * @param args Argumentos de linha de comando passados para a aplicação
	 */
	public static void main(String[] args) {
		SpringApplication.run(InclusiveaidApplication.class, args);
	}

}
