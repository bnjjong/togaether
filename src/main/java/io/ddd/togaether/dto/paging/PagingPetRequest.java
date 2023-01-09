/*
 * Created By dogfootmaster@gmail.com on 2023
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2023/01/09
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
 * create on 2023/01/09. create by IntelliJ IDEA.
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
@ToString(callSuper = true)
@JsonNaming(SnakeCaseStrategy.class)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PagingPetRequest extends PagingCommonRequest{
  private PetOrderBy petOrderBy;

  @Override
  protected Sort getSort() {
    if (petOrderBy == PetOrderBy.FOLLOWER_COUNT_DESC) {
      return Sort.by("followerCount").descending();
    }
    return Sort.by("id").descending();
  }

  public PagingPetRequest(int pageSize, int currentPage, PetOrderBy petOrderBy) {
    super(pageSize, currentPage);
    this.petOrderBy = petOrderBy;
  }
}
