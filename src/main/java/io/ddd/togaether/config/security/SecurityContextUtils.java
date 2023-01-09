/*
 * Copyright (C) 2018-2022 Bigin(https://bigin.io/main) - All Rights Reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 * Written by dev team, henry<jognsang@bigin.io>
 * Last modified on 2022/9/1
 */

package io.ddd.togaether.config.security;

import io.ddd.togaether.dao.MemberRepository;
import io.ddd.togaether.model.Member;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * create on 2022/09/01. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class SecurityContextUtils {
  private final MemberRepository memberRepository;

  /**
   * Get login member from security context.
   * TODO 시큐리티 적용 후 구현 필요.
   *
   * @return login member entity.
   */
  public Member getLoginMember() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    Member member = memberRepository.findByEmail(email)
        .orElseThrow(() -> {
          throw new EntityNotFoundException("login member is not found.");
        });
    // loading.
    member.getPets();
    return member;
//    return memberRepository.findByEmail("jongsang@naver.com")
//        .orElseThrow(() -> {
//          throw new EntityNotFoundException("login member is not found.");
//        });
  }
}
