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
  private Member owner;
  private String name;
  private Species species;
  private Character character;
  private Gender gender;
  private LocalDate birth;
  private PetImage mainImage;
  private List<PetImage> petImages;
  private String description;
  private String etc;
  private int likeCount;

  public PetDto(Long id, Member owner, String name, Species species, Character character,
      Gender gender, LocalDate birth, PetImage mainImage, List<PetImage> petImages,
      String description, String etc, int likeCount) {
    this.id = id;
    this.owner = owner;
    this.name = name;
    this.species = species;
    this.character = character;
    this.gender = gender;
    this.birth = birth;
    this.mainImage = mainImage;
    this.petImages = petImages;
    this.description = description;
    this.etc = etc;
    this.likeCount = likeCount;
  }
}
