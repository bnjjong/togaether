package io.ddd.togaether.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.ddd.togaether.model.Character;
import io.ddd.togaether.model.Gender;
import io.ddd.togaether.model.Member;
import io.ddd.togaether.model.PetImage;
import io.ddd.togaether.model.Species;
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
  private Long id;
  private String name;
  private Species species;
  private Character petCharacter;
  private Gender gender;
  private LocalDate birth;
  @Setter
  private String mainImage;
  private List<PetImage> petImages;
  private String description;
  private String etc;
  private int followerCount;

  public PetDto(Long id, Member owner, String name, Species species, Character petCharacter,
      Gender gender, LocalDate birth, String mainImage, List<PetImage> petImages,
      String description, String etc, int followerCount) {
    this.id = id;
    this.name = name;
    this.species = species;
    this.petCharacter = petCharacter;
    this.gender = gender;
    this.birth = birth;
    this.mainImage = mainImage;
    this.petImages = petImages;
    this.description = description;
    this.etc = etc;
    this.followerCount = followerCount;
  }
}
