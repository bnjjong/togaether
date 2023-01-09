package io.ddd.togaether.model;

import io.ddd.togaether.config.jpa.AuditEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners({AuditingEntityListener.class})
public class Pet extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  @ToString.Exclude
  private Member owner;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "follower_following",
      joinColumns = @JoinColumn(name = "pet_id"),
      inverseJoinColumns = @JoinColumn(name = "member_id")
  )
  @ToString.Exclude
  private Set<Member> follower = new HashSet<>();

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

  /**
   * 강아지 설명(특징)
   */
  @Column
  private String description;

  /**
   * 강아지 설명 기타
   */
  @Column
  private String etc;

  @Column
  private int followerCount = 0;


  @Builder
  public Pet(@NonNull Member owner, @NonNull String name, @NonNull Species species, @NonNull Character petCharacter, @NonNull Gender gender,
      LocalDate birth, String mainImage, String description, String etc) {
    this.owner = owner;
    this.name = name;
    this.species = species;
    this.petCharacter = petCharacter;
    this.gender = gender;
    this.birth = birth;
    this.mainImage = mainImage;
    this.description = description;
    this.etc = etc;
  }

  public void addFollower(Member memberBy) {
    if (follower.contains(memberBy)) {
      throw new IllegalStateException("this member already follow." + memberBy.getEmail());
    }
    this.follower.add(memberBy);
    this.followerCount++;
  }

  @Override
  public boolean equals(Object obj) {
    return this.id == ((Pet)obj).getId();
  }
}
