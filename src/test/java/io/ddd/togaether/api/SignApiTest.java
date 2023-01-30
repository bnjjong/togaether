/*
 * Created By dogfootmaster@gmail.com on 2023
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2023/01/27
 */

package io.ddd.togaether.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.ddd.togaether.config.CommonApiUnitTest;
import io.ddd.togaether.config.GlobalControllerExceptionHandler;
import io.ddd.togaether.config.security.MemberAuthentication;
import io.ddd.togaether.config.security.MemberSecurityService;
import io.ddd.togaether.config.security.SecurityContextUtils;
import io.ddd.togaether.config.security.jwt.JwtTokenProvider;
import io.ddd.togaether.config.security.jwt.MyToken;
import io.ddd.togaether.dto.MemberDto;
import io.ddd.togaether.dto.SignInRequest;
import io.ddd.togaether.dto.SignupRequest;
import io.ddd.togaether.model.AuthGrade;
import io.ddd.togaether.model.Member;
import io.ddd.togaether.service.PetService;
import io.ddd.togaether.test.member.MemberFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * create on 2023/01/27. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han
 * @version 1.0
 * @see
 * @since 1.0
 */
class SignApiTest extends CommonApiUnitTest {

  private String baseUrl = "/sign-in";

  @MockBean
  private MemberSecurityService memberSecurityService;

  @MockBean
  private PasswordEncoder passwordEncoder;

  @MockBean
  private JwtTokenProvider tokenProvider;

  @Override
  protected Object getController() {
    return new SignApi(memberSecurityService, passwordEncoder, tokenProvider);
  }

  @Override
  protected Object getControllerAdvice() {
    return new GlobalControllerExceptionHandler();
  }

  @Nested
  class SignMemberSignIn {
    @Test
    @DisplayName("로그인")
    void success() throws Exception {
      // given
      MemberDto dto = MemberFixture.memberDto1();
      final String password = "abcd1234";
      SignInRequest request = new SignInRequest(dto.getEmail(),password);
      Member member = objectMapper.convertValue(dto, Member.class);
      member.addAuthority(AuthGrade.NORMAL);


      given(memberSecurityService.loadUserByUsername(request.getEmail())).willReturn(
        new MemberAuthentication(member)
      );
      given(passwordEncoder.matches(any(),any())).willReturn(true);
      given(tokenProvider.createToken(any(),any())).willReturn(
          new MyToken(
              "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb25nc2FuZ0B0b2dhZXRoZXIuaW8iLCJpc3MiOiJUT0dBRVRIRVIiLCJhdWQiOiJVU0VSIiwicm9sZXMiOlsiTk9STUFMIl0sImlhdCI6MTY3NDc0NzMyNywiZXhwIjoxNzA2MjgzMzI3fQ.8N9dc16uG-GLW1CcSkg0QQgNWHTpm04FHMSmR2ObBlY",
              "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb25nc2FuZ0B0b2dhZXRoZXIuaW8iLCJpc3MiOiJUT0dBRVRIRVIiLCJhdWQiOiJVU0VSIiwicm9sZXMiOlsiTk9STUFMIl0sImlhdCI6MTY3NDc0NzMyNywiZXhwIjoxNjc0ODMzNzI3fQ.-BZ55iWi3WcgtA7tGppK5wR7zVotgk5PsDu02RY7tFw"
          )

      );


      //when then
      mvc.perform(post(baseUrl+"/member")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(request)))
          // then
          .andExpectAll(
              status().isOk(),
              content().contentType(MediaType.APPLICATION_JSON)
          )
          .andDo(print());
    }
  }
}