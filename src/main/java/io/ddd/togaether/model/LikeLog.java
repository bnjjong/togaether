/*
 * Created By dogfootmaster@gmail.com on 2023
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2023/01/05
 */

package io.ddd.togaether.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

/**
 * create on 2023/01/05. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han
 * @version 1.0
 * @see
 * @since 1.0
 */
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeLog {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  @ToString.Exclude
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pet_id")
  @ToString.Exclude
  private Pet pet;

  /**
   * 생성 일.
   */
  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @Builder
  public LikeLog(@NonNull Member member, @NonNull Pet pet) {
    this.member = member;
    this.pet = pet;
  }
}
