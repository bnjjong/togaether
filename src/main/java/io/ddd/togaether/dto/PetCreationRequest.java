package io.ddd.togaether.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.ddd.togaether.model.Character;
import io.ddd.togaether.model.Gender;
import io.ddd.togaether.model.Species;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * create on 2022/12/19. create by IntelliJ IDEA.
 *
 * <p> 강아지(펫) 등록 요청 DTO class </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see io.ddd.togaether.model.Pet
 * @since 1.0
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@JsonNaming(SnakeCaseStrategy.class)
public class PetCreationRequest {

  /**
   * 펫 이름
   */
  @NotBlank
  private String name;

  /**
   * 강아지 (종)
   */
  @NotNull
  private Species species;
  /**
   * 성격
   */
  @NotNull
  private Character petCharacter;
  /**
   * 성별
   */
  @NotNull
  private Gender gender;
  /**
   * 생년 월일.
   */
  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birth;
  /**
   * 강아지 설명.
   */
  private String description;
  /**
   * 강아지 기타.
   */
  private String etc;


  public PetCreationRequest(String name, Species species, Character petCharacter, Gender gender,
      LocalDate birth, String description, String etc) {
    this.name = name;
    this.species = species;
    this.petCharacter = petCharacter;
    this.gender = gender;
    this.birth = birth;
    this.description = description;
    this.etc = etc;
  }
}
