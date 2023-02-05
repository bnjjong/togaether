package io.ddd.togaether.dao;

import io.ddd.togaether.model.Content;
import io.ddd.togaether.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * create on 2022/12/14. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
public interface ContentRepository extends JpaRepository<Content, Long> {

}
