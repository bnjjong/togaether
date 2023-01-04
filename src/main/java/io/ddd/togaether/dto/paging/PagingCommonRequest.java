/*
 * Copyright (C) 2018-2022 Bigin(https://bigin.io/main) - All Rights Reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 * Written by dev team, henry<jognsang@bigin.io>
 * Last modified on 2022/8/17
 */

package io.ddd.togaether.dto.paging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@JsonNaming(SnakeCaseStrategy.class)
public abstract class PagingCommonRequest {

  /**
   * page size. (한 화면에 보여줄 컨텐츠 개수)
   */
  @Min(value = 0, message = "A value of \"page_size\" field must be over than 0.")
  private int pageSize;

  /**
   * 조회 할 페이지 번호.
   */
  @Min(value = 0, message = "A value of \"current_page\" field must be over than 0.")
  private int currentPage;

  protected PagingCommonRequest() {
  }

  /**
   * page 번호는 0부터 시작하므로 -1을 꼭 해준다.
   *
   * @return {@code Pageable} object.
   */
  @JsonIgnore
  public Pageable getPageable() {
    return PageRequest.of(currentPage -1, pageSize, getSort());
  }

  protected abstract Sort getSort();

  protected PagingCommonRequest(int pageSize, int currentPage) {
    this.pageSize = pageSize;
    this.currentPage = currentPage;
  }
}
