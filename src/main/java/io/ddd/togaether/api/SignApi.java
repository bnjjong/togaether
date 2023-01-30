/*
 * Copyright (C) 2018-2022 Bigin(https://bigin.io/main) - All Rights Reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 * Written by dev team, henry<jognsang@bigin.io>
 * Last modified on 2022/8/24
 */

package io.ddd.togaether.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.ddd.togaether.config.security.MemberSecurityService;
import io.ddd.togaether.config.security.jwt.JwtTokenProvider;
import io.ddd.togaether.dto.SignInRequest;
import io.ddd.togaether.dto.SignInResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import javax.security.auth.login.CredentialException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create on 2022/07/22. create by IntelliJ IDEA.
 *
 * <p> Sign in rest controller. it support jwt login. </p>
 *
 * @author Jongsang Han
 * @version 1.0
 * @since 1.0
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sign-in")
public class SignApi {

  private final MemberSecurityService memberSecurityService;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider tokenProvider;


  /**
   * <p> member 로그인 요청. </p>
   *
   * @param request 로그인 정보
   * @return {@code ResponseCommonWrapper} with {@link SignInResponse}.
   */
  @PostMapping(value = "/member", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<SignInResponse> memberSignIn(
      @Valid @RequestBody final SignInRequest request) throws CredentialException {

    final String email = request.getEmail();
    final String password = request.getPassword();
    UserDetails member = memberSecurityService.loadUserByUsername(request.getEmail());


    if (!passwordEncoder.matches(password, member.getPassword())) {
      throw new CredentialException("Sign-in has been failed.");
    }

    final List<String> authorities = member.getAuthorities()
        .stream()
        .map(a -> a.getAuthority().toString())
        .collect(Collectors.toList());
    final SignInResponse response = new SignInResponse(
        member.getUsername(),
        authorities,
        tokenProvider.createToken(email, authorities)
    );

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
