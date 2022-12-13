package io.ddd.togaether.dto;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Slf4j
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
