package io.ddd.togaether.model;

import io.ddd.togaether.config.jpa.AuditEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
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
public class Member extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;


  @Column
  private LocalDate birth;

  @Column
  private String profilePicturePath;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "owner")
  @ToString.Exclude
  private List<Pet> pets;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "member")
  @ToString.Exclude
  private List<MemberAuthority> authorities;


  @Builder
  public Member(@NonNull String email, @NonNull String password, @NonNull String name,
      LocalDate birth, List<Pet> pets,
      List<MemberAuthority> authorities) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.birth = birth;
    this.pets = pets;
    this.authorities = authorities;
  }

  public void addAuthority(AuthGrade authGrade) {
    this.authorities = List.of(new MemberAuthority(this, authGrade.toString()));
  }

  public void updateProfilePicture(String profilePicturePath) {
    this.profilePicturePath = profilePicturePath;
  }
}
