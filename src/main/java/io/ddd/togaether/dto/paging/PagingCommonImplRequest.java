/*
 * Copyright (C) 2018-2022 Bigin(https://bigin.io/main) - All Rights Reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 * Written by dev team, henry<jognsang@bigin.io>
 * Last modified on 2022/9/23
 */

package io.ddd.togaether.dto.paging;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Sort;

/**
 * create on 2022/08/17. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
@Getter
@ToString(callSuper = true)
@JsonNaming(SnakeCaseStrategy.class)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PagingCommonImplRequest extends PagingCommonRequest{

  @Override
  protected Sort getSort() {
    return Sort.by("id").descending();
  }

  public PagingCommonImplRequest(int pageSize, int currentPage) {
    super(pageSize, currentPage);
  }
}
