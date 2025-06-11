package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para operações de banco de dados relacionadas aos usuários.
 * Fornece métodos para persistência e consulta de usuários.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Busca um usuário pelo email.
     * @param email Email do usuário
     * @return Optional contendo o usuário, se encontrado
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Verifica se existe um usuário com o email especificado.
     * @param email Email a ser verificado
     * @return true se o email já está em uso, false caso contrário
     */
    boolean existsByEmail(String email);
}