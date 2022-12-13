package io.ddd.togaether.dao;

import io.ddd.togaether.model.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * create on 2022/12/13. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByEmail(String email);
}
