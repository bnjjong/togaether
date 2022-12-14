/*
 * Copyright (C) 2018-2022 Bigin(https://bigin.io/main) - All Rights Reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 * Written by dev team, hanjongsang<jognsang@bigin.io>
 * Last modified on 2022/7/23
 */

package io.ddd.togaether.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * create on 2022/07/23. create by IntelliJ IDEA.
 *
 * <p> 인증 에서 실패할 경우 여기에서 handling 된다.  </p>
 * <li>Token이 없이 호출한 경우</li>
 * <li>Token이 유효기간이 끝났을 경우</li>
 *
 * @author Jongsang Han
 * @version 1.0
 * @since 1.0
 */
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {

    final ObjectMapper objectMapper = new ObjectMapper();

    final String errorMsg = String.format("message:{%s}",
        exception.getMessage());

    ResponseEntity<String> responseEntity = new ResponseEntity<>(errorMsg, HttpStatus.UNAUTHORIZED);

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");

    response.getWriter().println(objectMapper.writeValueAsString(responseEntity));
  }
}
