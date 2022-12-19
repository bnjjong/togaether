package io.ddd.togaether.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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

  @Column
  @Enumerated(EnumType.STRING)
  private Species species;

  @Column
  @Enumerated(EnumType.STRING)
  private Character petCharacter;

  @Column
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column
  private LocalDate birth;

  @Column
  private String mainImage;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pet")
  @ToString.Exclude
  private List<PetImage> petImages;

  @Column
  private int likeCount = 0;


  @Builder
  public Pet(@NonNull Member owner, @NonNull String name, @NonNull Species species, @NonNull Character petCharacter, @NonNull Gender gender,
      LocalDate birth, String mainImagePath) {
    this.owner = owner;
    this.name = name;
    this.species = species;
    this.petCharacter = petCharacter;
    this.gender = gender;
    this.birth = birth;
    this.mainImage = mainImagePath;
  }
}
