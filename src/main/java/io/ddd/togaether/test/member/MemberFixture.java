/*
 * Created By dogfootmaster@gmail.com on 2023
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2023/01/09
 */

package io.ddd.togaether.test.member;

import io.ddd.togaether.dto.MemberDto;
import io.ddd.togaether.dto.SignupRequest;
import java.time.LocalDate;

/**
 * create on 2023/01/09. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han
 * @version 1.0
 * @see
 * @since 1.0
 */
public class MemberFixture {
  public static SignupRequest memberForSignup1() {
    return SignupRequest.builder()
        .email("jongsang@togaether.io")
        .password("abcd1234")
        .name("Henry")
        .birth(LocalDate.of(1985,6,19))
        .build();
  }

  public static SignupRequest memberForSignup2() {
    return SignupRequest.builder()
        .email("taein@togaether.io")
        .password("abcd1234")
        .name("Denny")
        .birth(LocalDate.of(1999,1,1))
        .build();
  }

  public static SignupRequest memberForSignup3() {
    return SignupRequest.builder()
        .email("jamit@togaether.io")
        .password("abcd1234")
        .name("Jamit")
        .birth(LocalDate.of(1995,11,11))
        .build();
  }

  public static SignupRequest memberForSignupTest() {
    return SignupRequest.builder()
        .email("test@togaether.io")
        .password("Abcd!23$")
        .name("test")
        .birth(LocalDate.of(1999,1,1))
        .build();
  }

  public static MemberDto memberDto1() {
    return new MemberDto(1L,
        "jongsang@togaether.io",
        "Henry",
        LocalDate.of(1985,6,19)
        );
  }

}
