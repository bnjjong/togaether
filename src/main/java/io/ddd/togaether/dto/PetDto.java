package io.ddd.togaether.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.ddd.togaether.model.Character;
import io.ddd.togaether.model.Gender;
import io.ddd.togaether.model.Member;
import io.ddd.togaether.model.PetImage;
import io.ddd.togaether.model.Species;
import jakarta.annotation.Nonnull;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * create on 2022/12/14. create by IntelliJ IDEA.
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
public class PetDto {

  /**
   * pet Id.
   */
  @Setter
  private Long id;

  /**
   * Pet 이름.
   */
  private String name;

  /**
   * 강아지 (종)
   */
  private Species species;
  /**
   * 강아지 (성격)
   */
  @Nonnull()
  private Character petCharacter;
  /**
   * 성별
   */
  private Gender gender;
  /**
   * 생년 월일.
   */
  private LocalDate birth;

  /**
   * 메인 이미지 path.
   */
  @Setter
  private String mainImage;

  /**
   * 추가 설명
   */
  private String description;
  /**
   * 기타.
   */
  private String etc;
  /**
   * 팔로우 카운트
   */
  private int followerCount;

  public PetDto(Long id, Member owner, String name, Species species, Character petCharacter,
      Gender gender, LocalDate birth, String mainImage,
      String description, String etc, int followerCount) {
    this.id = id;
    this.name = name;
    this.species = species;
    this.petCharacter = petCharacter;
    this.gender = gender;
    this.birth = birth;
    this.mainImage = mainImage;
    this.description = description;
    this.etc = etc;
    this.followerCount = followerCount;
  }
}
