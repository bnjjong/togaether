/*
 * Created By dogfootmaster@gmail.com on 2023
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2023/01/05
 */

package io.ddd.togaether.config.jpa;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

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
@Configuration
@Slf4j
@EnableJpaAuditing
public class AuditEntityConfiguration {
  @Bean
  public AuditorAware<String> auditorAware() {
    return () -> Optional.of(
        SecurityContextHolder.getContext().getAuthentication() == null ? "ANONYMOUS" :
            SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")
                ? "ANONYMOUS"
                : SecurityContextHolder.getContext().getAuthentication().getName()
    );
  }
}


