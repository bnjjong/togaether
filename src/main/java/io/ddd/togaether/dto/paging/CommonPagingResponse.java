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

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * create on 2022/07/08. create by IntelliJ IDEA.
 *
 * <p> Company Common Response </p>
 *
 * @author Mingi Park(tom)
 * @version 1.0
 * @since 1.0
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@JsonNaming(SnakeCaseStrategy.class)
public class CommonPagingResponse<T> {

  /**
   * 데이터 총 개수
   */
  @NotNull
  private long totalCount;

  /**
   * 총 페이지 수
   */
  @NotNull
  private int totalPages;

  /**
   * 조회된 페이지
   */
  @NotNull
  private int currentPage;

  /**
   * 데이터 목록
   */
  private List<T> rows;

  public CommonPagingResponse(long totalCount, int totalPages, int currentPage, List<T> rows) {
    this.totalCount = totalCount;
    this.totalPages = totalPages;
    this.currentPage = currentPage + 1;
    this.rows = rows;
  }
}