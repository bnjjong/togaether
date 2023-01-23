package io.ddd.togaether.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * create on 2022/12/22. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@JsonNaming(SnakeCaseStrategy.class)
public class SpeciesDto {

  /**
   * 코드 네임 (API 요청시 사용)
   */
  @NotBlank
  private String codeName;

  /**
   * 강아지 종 한국어 명
   */
  @NotBlank
  private String korName;

  /**
   * 인덱스 코드 (한글)
   */
  @NotBlank
  private String indexCode;

  public SpeciesDto(@NonNull String codeName, @NonNull String korName, @NonNull String indexCode) {
    this.codeName = codeName;
    this.korName = korName;
    this.indexCode = indexCode;
  }
}
