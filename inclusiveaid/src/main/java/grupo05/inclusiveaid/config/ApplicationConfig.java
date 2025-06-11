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
 * Configuração geral da aplicação.
 * Define beans essenciais para autenticação e segurança.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    /**
     * Configura o serviço de detalhes do usuário.
     * Carrega usuários do banco de dados para autenticação.
     * @return Serviço de detalhes do usuário configurado
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    /**
     * Configura o provedor de autenticação.
     * Define como os usuários serão autenticados.
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
     * Gerencia o processo de autenticação.
     * @param config Configuração de autenticação
     * @return Gerenciador de autenticação configurado
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configura o codificador de senha.
     * Define o algoritmo de criptografia para senhas.
     * @return Codificador de senha configurado
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
} 