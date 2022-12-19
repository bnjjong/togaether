package io.ddd.togaether.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.ddd.togaether.model.Character;
import io.ddd.togaether.model.Gender;
import io.ddd.togaether.model.Species;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * create on 2022/12/19. create by IntelliJ IDEA.
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
public class PetCreationRequest {

  private String name;
  private Species species;
  private Character petCharacter;
  private Gender gender;
  private LocalDate birth;
}
