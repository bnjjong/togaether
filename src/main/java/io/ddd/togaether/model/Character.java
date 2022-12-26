package io.ddd.togaether.model;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor
public enum Character {
  LITTLE_TIMID("조금 소심한"),
  FRIENDLY("다정 다감한"),
  TEACHABLE("잘 배우는"),
  CURIOUS("호기심 많은"),
  CALM("조용한게 좋은"),
  SOCIABLE("친화력이 좋은"),
  SENSITIVE("살짝 예민한"),
  ENERGETIC("에너자이저"),
  LOYALTY("주인 바라기"),
  RELAXED("느긋한"),
  LIKE_ATTENTION("관심 받는 것을 좋아 하는"),
  APPETITE("먹는 것을 좋아 하는"),
  WELL_TRAINED("훈련 잘되는 강아지"),

  ;

  private final String korText;

  public static List<Character> getAll() {
    return List.of(Character.values());
  }
}
