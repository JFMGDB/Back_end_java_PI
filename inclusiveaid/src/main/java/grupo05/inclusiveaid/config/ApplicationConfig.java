package grupo05.inclusiveaid.config;

import grupo05.inclusiveaid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuração geral da aplicação InclusiveAID.
 * Esta classe define os beans essenciais para o funcionamento da autenticação
 * e segurança da aplicação, incluindo:
 * - Serviço de detalhes do usuário
 * - Provedor de autenticação
 * - Gerenciador de autenticação
 * - Codificador de senha
 * 
 * Utiliza injeção de dependência para acessar o repositório de usuários
 * e configura todos os componentes necessários para o processo de autenticação.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    /**
     * Configura o serviço de detalhes do usuário.
     * Este serviço é responsável por carregar os dados do usuário do banco de dados
     * durante o processo de autenticação.
     * 
     * Implementação:
     * - Busca o usuário pelo email no repositório
     * - Lança exceção se o usuário não for encontrado
     * 
     * @return Serviço de detalhes do usuário configurado
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    /**
     * Configura o provedor de autenticação.
     * Define como os usuários serão autenticados no sistema,
     * utilizando o serviço de detalhes do usuário e o codificador de senha.
     * 
     * Implementação:
     * - Utiliza DaoAuthenticationProvider para autenticação baseada em banco de dados
     * - Configura o serviço de detalhes do usuário
     * - Utiliza BCrypt para verificação de senha
     * 
     * @return Provedor de autenticação configurado
     */
    @Bean
    @SuppressWarnings("deprecation")
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService());
        return authProvider;
    }

    /**
     * Configura o gerenciador de autenticação.
     * Este bean é responsável por gerenciar todo o processo de autenticação,
     * incluindo a validação de credenciais e a criação de tokens de autenticação.
     * 
     * @param config Configuração de autenticação do Spring Security
     * @return Gerenciador de autenticação configurado
     * @throws Exception Se houver erro na configuração
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configura o codificador de senha.
     * Define o algoritmo de criptografia que será utilizado para
     * armazenar e verificar senhas de usuários.
     * 
     * Implementação:
     * - Utiliza BCrypt como algoritmo de hash
     * - Inclui salt automático para maior segurança
     * - Configura força de trabalho padrão do BCrypt
     * 
     * @return Codificador de senha configurado
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
} 