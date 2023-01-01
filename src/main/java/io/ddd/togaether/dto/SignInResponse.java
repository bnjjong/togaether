/*
 * Copyright (C) 2018-2022 Bigin(https://bigin.io/main) - All Rights Reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 * Written by dev team, henry<jognsang@bigin.io>
 * Last modified on 2022/8/24
 */

package io.ddd.togaether.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.ddd.togaether.config.security.jwt.MyToken;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * create on 2022/07/22. create by IntelliJ IDEA.
 *
 * <pre>
 * 로그인 성공 시 전송하는 login dto class.
 * jwt 토큰을 반환하며 이를 통해 각 서비스 별로 인증, 인가를 받을 수 있다.
 * </pre>
 *
 * @author Jongsang Han
 * @version 1.0
 * @since 1.0
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@JsonNaming(SnakeCaseStrategy.class)
public class SignInResponse {

  /**
   * email.
   */
  @NotBlank
  private String email;

  /**
   * roles.
   */
  @NotBlank
  private List<String> role;

  /**
   * token.
   */
  @NotBlank
  private MyToken jwt;

  /**
   * SignInResponse constructor.
   *
   * @param email not allow null.
   * @param role ADMIN, OWNER, OPERATOR
   * @param jwt token for security.
   */
  public SignInResponse(@NonNull String email, @NonNull List<String> role, @NonNull MyToken jwt) {
    this.email = email;
    this.role = role;
    this.jwt = jwt;
  }


}
