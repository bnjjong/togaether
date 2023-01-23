package io.ddd.togaether.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
public class SignupRequest {

  /**
   * E-mail.
   */
  @Email(message = "A \"email\" field is required email format.")
  @NotBlank(message = "A \"email\" field is missing or its value is blank.")
  private String email;

  /**
   * 비밀 번호.
   */
  @NotBlank(message = "A \"password\" field is missing or its value is blank.")
  @Pattern(regexp = "^[a-zA-Z\\d~!@#$%^&*()_+|<>?:{}]{8,20}$",
      message = "A \"password\" field should be more than 8 and less then 20 characters.")
  private String password;
  /** No constructor or factory method candid
   * 이름.
   */
  @NotBlank(message = "A \"name\" field is missing or its value is blank.")
  private String name;

  /**
   * 생년 월일
   */
  @NotNull(message = "A \"birth\" field is missing.")
  private LocalDate birth;


  @Builder
  public SignupRequest(@NonNull String email, @NonNull String password, @NonNull String name, @NonNull LocalDate birth) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.birth = birth;
  }
}
