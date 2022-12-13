package io.ddd.togaether.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
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
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  @ToString.Exclude
  private Member owner;
  @Column
  private String name;

  @Column(nullable = true)
  private Species species;

  @Column
  private Character character;

  @Column
  private Gender gender;

  @Column
  private LocalDate birth;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pet")
  @ToString.Exclude
  private List<PetImage> petImages;

  @Column
  private int likeCount = 0;


}
