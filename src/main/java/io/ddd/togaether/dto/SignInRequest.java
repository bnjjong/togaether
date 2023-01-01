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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * create on 2022/07/22. create by IntelliJ IDEA.
 *
 * <p> Login request dto class. </p>
 *
 * @author Jongsang Han
 * @version 1.0
 * @since 1.0
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@JsonNaming(SnakeCaseStrategy.class)
public class SignInRequest {

  /**
   * 이메일.
   */
  @Email(message = "A \"email\" field is required email format.")
  @NotBlank(message = "A \"email\" field is missing or its value is blank.")
  private String email;

  /**
   * 비밀번호.
   */
  @NotBlank(message = "A \"password\" field is missing or its value is blank.")
  private String password;

  public SignInRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
