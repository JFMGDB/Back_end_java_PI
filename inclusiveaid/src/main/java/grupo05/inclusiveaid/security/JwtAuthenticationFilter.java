package grupo05.inclusiveaid.security;

import com.company.aid.service.impl.AuthServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtUtil jwtUtil;
  private final AuthServiceImpl authService;

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws java.io.IOException, jakarta.servlet.ServletException {
    final var authHeader = req.getHeader("Authorization");
    String username = null, token = null;
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      token = authHeader.substring(7);
      if (jwtUtil.validateToken(token)) {
        username = jwtUtil.extractUsername(token);
      }
    }
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      var userDetails = authService.loadUserByUsername(username);
      var authToken = new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());
      authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
      SecurityContextHolder.getContext().setAuthentication(authToken);
    }
    chain.doFilter(req, res);
  }
}