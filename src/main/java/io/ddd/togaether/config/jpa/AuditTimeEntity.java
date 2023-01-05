/*
 * Created By dogfootmaster@gmail.com on 2023
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2023/01/05
 */

package io.ddd.togaether.config.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditTimeEntity {

  /**
   * 생성 일.
   */
  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdAt;

  /**
   * 수정 일.
   */
  @LastModifiedDate
  private LocalDateTime modifiedAt;
}

