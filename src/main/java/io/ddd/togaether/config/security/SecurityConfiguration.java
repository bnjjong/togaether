package io.ddd.togaether.config.security;

import io.ddd.togaether.config.security.jwt.JwtAccessDeniedHandler;
import io.ddd.togaether.config.security.jwt.JwtAuthenticationEntryPoint;
import io.ddd.togaether.config.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
@EnableWebSecurity
@EnableMethodSecurity // pre post로 권한 체크를 하겠다.
@Configuration
@Slf4j
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter filter;


  /**
   * configure http security.
   *
   * @param httpSecurity {@link HttpSecurity}.
   * @return SecurityFilterChain with configured.
   * @throws Exception when httpSecurity.httpBasic() is not valid.
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .httpBasic().disable() // ui 비 활성화
        .csrf().disable()
        .cors().disable()

        .sessionManagement()
        .sessionCreationPolicy(
            SessionCreationPolicy.STATELESS
        )
        .and()
        .addFilterAt(filter,
            UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests()
        .requestMatchers("/config/**").permitAll()
//        .antMatchers("/admin/**").permitAll()
//        .antMatchers(HttpMethod.POST, "/admin").permitAll() // 회원 가입
        .requestMatchers("/system/hc").permitAll()
        .requestMatchers("/etc/**").permitAll()
        .requestMatchers("/company/**").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
        .accessDeniedHandler(new JwtAccessDeniedHandler());

    return httpSecurity.build();
  }

}
