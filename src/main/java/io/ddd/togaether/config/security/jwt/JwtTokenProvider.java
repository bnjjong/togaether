/*
 * Copyright (C) 2018-2022 Bigin(https://bigin.io/main) - All Rights Reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 * Written by dev team, henry<jognsang@bigin.io>
 * Last modified on 2022/7/21
 */

package io.ddd.togaether.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * create on 2022/07/21. create by IntelliJ IDEA.
 *
 * <p> JwtTokenProvider, Spring security custom provider. </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @since 1.0
 */
@Component
@Slf4j
public class JwtTokenProvider {

  public static final String AUTHORIZATION_HEADER = "Authorization";

  public static final String BEARER_PREFIX = "Bearer ";


  @Value("${token.issuer}")
  private String issuer;
  @Value("${token.audience}")
  private String audience;

  private final String secretKey;

  private final long accessTokenValidityInMilliseconds;
  private final long refreshTokenValidityInMilliseconds;

  public JwtTokenProvider(@Value("${token.secret}") String secretKey,
      @Value("${token.access.expiration_time}") long accessTokenValidityInMilliseconds,
      @Value("${token.refresh.expiration_time}") long refreshTokenValidityInMilliseconds
  ) {
    this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds * 1000;
    this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds * 1000;
  }


//  public String createToken(String email, List<String> roles) {
//    log.info("start creating token!!");
//    Map<String, Object> header = new HashMap<>();
//    header.put(Header.TYPE, Header.JWT_TYPE);
//    Claims claims = Jwts.claims();
//    claims.setSubject(email);
//    claims.setIssuer(issuer);
//    claims.setAudience(audience);
//    claims.put("roles", roles);
//    Date now = new Date();
//
//    String token = Jwts.builder()
//        .setHeader(header)
//        .setClaims(claims)
//        .setIssuedAt(now)
//        .setExpiration(new Date(now.getTime() + accessTokenValidityInMilliseconds))
//        .signWith(SignatureAlgorithm.HS256, secretKey)
//        .compact();
//
//    log.info("end creating token!!");
//
//    return token;
//  }

  /**
   * <p> ????????? ????????? JWT ????????? ?????? ??????.</p>
   *
   * @param email
   * @param roles
   * @return Token {@code String}.
   */
  public MyToken createToken(String email, List<String> roles) {
    Map<String, Object> header = new HashMap<>();
    header.put(Header.TYPE, Header.JWT_TYPE);
    Claims claims = Jwts.claims();
    claims.setSubject(email);
    claims.setIssuer(issuer);
    claims.setAudience(audience);
    claims.put("roles", roles);
    Date now = new Date();

    String accessToken = Jwts.builder()
        .setHeader(header)
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + accessTokenValidityInMilliseconds))
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();

    String refreshToken = Jwts.builder()
        .setHeader(header)
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + refreshTokenValidityInMilliseconds))
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();

    return new MyToken(accessToken, refreshToken);
  }

  /**
   * <p> ????????? ???????????? ?????? SecurityContextHolder??? ????????? Authentication ?????? ???. </p>
   *
   * @param token
   * @param userDetailsService
   * @return ???????????? Admin ????????? ????????? {@code Authentication} Object.
   * @throws UsernameNotFoundException ?????? token(email)??? ????????? ?????? ??? ?????? ???.
   */
  public Authentication getAuthentication(String token, UserDetailsService userDetailsService) {
    log.info("[getAuthentication] ?????? ?????? ?????? ?????? ??????");
    String subject = this.getSubject(token);
    UserDetails userDetails = userDetailsService.loadUserByUsername(subject);

    log.info("[getAuthentication] ?????? ?????? ?????? ?????? ??????, UserDetails UserName : {}",
        userDetails.getUsername());
    return new UsernamePasswordAuthenticationToken(userDetails, "",
        userDetails.getAuthorities());
  }

  /**
   * retrieve email from token.
   *
   * @param token
   * @return email.
   */
  public String getSubject(String token) {
    log.info("[getUsername] ?????? ?????? ?????? ?????? ?????? ??????");
    String info = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
        .getSubject();
    log.info("[getUsername] ?????? ?????? ?????? ?????? ?????? ?????? ??????, info : {}", info);
    return info;
  }

  public String getRoles(String token) {
    return (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
        .get("roles", List.class).get(0);
  }

  /**
   * HTTP Request Header ??? ????????? ?????? ?????? ?????????
   *
   * @param request Http Request Header
   * @return String type Token ???
   */
  public String resolveToken(HttpServletRequest request) {
    log.info("[resolveToken] HTTP ???????????? Token ??? ??????");

    String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
      return bearerToken.replace(BEARER_PREFIX, "");
    }

    return null;
  }


//  public boolean validateToken(String token) {
//    try {
//      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//      return true;
//    } catch (SignatureException e) {
//      log.warn("Invalid JWT signature trace: {}", e);
//    } catch (MalformedJwtException e) {
//      log.warn("Invalid JWT token trace: {}", e);
//    } catch (ExpiredJwtException e) {
//      log.warn("Expired JWT token trace: {}", e);
//    } catch (UnsupportedJwtException e) {
//      log.warn("Unsupported JWT token trace: {}", e);
//    } catch (IllegalArgumentException e) {
//      log.warn("JWT token compact of handler are invalid trace: {}", e);
//    }
//    return false;
//  }

  /**
   * ????????? ?????? ?????? ?????? ??????.
   *
   * @param token
   * @return {@code true} if token is valid.
   * @throws BadCredentialsException
   * @throws ExpiredJwtException
   */
  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return true;
    } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
      throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
    } catch (ExpiredJwtException ex) {
      throw ex;
    }
  }
}
