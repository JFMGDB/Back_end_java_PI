package grupo05.inclusiveaid.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Serviço responsável pela geração e validação de tokens JWT.
 * Gerencia a criação, extração e verificação de tokens de autenticação.
 */
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /**
     * Extrai o nome de usuário do token JWT.
     * @param token Token JWT
     * @return Nome de usuário extraído do token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrai uma reivindicação específica do token JWT.
     * @param token Token JWT
     * @param claimsResolver Função para extrair a reivindicação desejada
     * @return Valor da reivindicação extraída
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Gera um token JWT para um usuário.
     * @param userDetails Detalhes do usuário
     * @return Token JWT gerado
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Gera um token JWT com reivindicações extras.
     * @param extraClaims Reivindicações adicionais
     * @param userDetails Detalhes do usuário
     * @return Token JWT gerado
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Verifica se um token JWT é válido para um usuário.
     * @param token Token JWT
     * @param userDetails Detalhes do usuário
     * @return true se o token for válido, false caso contrário
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Verifica se um token JWT está expirado.
     * @param token Token JWT
     * @return true se o token estiver expirado, false caso contrário
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrai a data de expiração do token JWT.
     * @param token Token JWT
     * @return Data de expiração do token
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrai todas as reivindicações do token JWT.
     * @param token Token JWT
     * @return Todas as reivindicações do token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Obtém a chave de assinatura para o token JWT.
     * @return Chave de assinatura
     */
    private Key getSigningKey() {
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
} 