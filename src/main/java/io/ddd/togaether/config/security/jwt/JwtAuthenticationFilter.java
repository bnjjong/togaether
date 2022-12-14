package io.ddd.togaether.config.security.jwt;

import io.ddd.togaether.config.security.MemberSecurityService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * create on 2022/12/14. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtTokenProvider jwtTokenProvider;
  private final MemberSecurityService memberSecurityService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String token = jwtTokenProvider.resolveToken(request);

    if (token == null) {
      request.setAttribute("StatusCode", HttpStatus.FORBIDDEN);
    } else if (!jwtTokenProvider.validateToken(token)) {
      request.setAttribute("StatusCode", HttpStatus.FORBIDDEN);
    } else {
      UserDetailsService service = memberSecurityService;
      Authentication authentication = jwtTokenProvider.getAuthentication(token, service);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      log.info("token 인증 완료!");
    }
    filterChain.doFilter(request, response);
  }
}
