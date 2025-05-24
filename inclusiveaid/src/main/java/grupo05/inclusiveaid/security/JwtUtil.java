package grupo05.inclusiveaid.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
  private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  private final long validity = 3600_000; // 1h

  public String generateToken(String username) {
    var now = new Date();
    return Jwts.builder()
      .setSubject(username)
      .setIssuedAt(now)
      .setExpiration(new Date(now.getTime() + validity))
      .signWith(key)
      .compact();
  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build()
      .parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build()
        .parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }
}