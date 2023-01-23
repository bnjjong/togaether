package io.ddd.togaether.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * create on 2022/12/11. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han(Henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@JsonNaming(SnakeCaseStrategy.class)
public class MemberDto {

  /**
   * member id.
   */
  @NotNull
  private Long id;

  /**
   * email.
   */
  @NotBlank
  private String email;

  /**
   * 회원 이름
   */
  @NotBlank
  private String name;

  /**
   * 회원 생일
   */
  private LocalDate birth;

  /**
   * 회원 프로필 url
   */
  @Setter
  private String myProfilePictureUrl;

  public MemberDto(@NonNull Long id, @NonNull String email, @NonNull String name,  LocalDate birth) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.birth = birth;
  }
}
