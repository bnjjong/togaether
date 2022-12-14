package io.ddd.togaether.dto;

import static lombok.AccessLevel.PACKAGE;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor(access = PACKAGE)
@JsonNaming(SnakeCaseStrategy.class)
public class MemberDto {
  private Long id;
  private String email;
  private String name;
  private LocalDate birth;

  public MemberDto(Long id, String email, String name, LocalDate birth) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.birth = birth;
  }
}
