package io.ddd.togaether.dto.mapper;

import io.ddd.togaether.dto.MemberDto;
import io.ddd.togaether.model.Member;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.ap.shaded.freemarker.template.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * create on 2022/12/23. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
public abstract class MemberMapperDecorator implements MemberMapper{

  @Autowired
  @Qualifier("delegate")
  private MemberMapper delegate;

  @Override
  public MemberDto toDto(Member entity) {
    MemberDto memberDto = delegate.toDto(entity);
    if (StringUtils.isEmpty(entity.getProfilePicturePath())) {
      return memberDto;
    }
    memberDto.setMyProfilePictureUrl("/member/my-profile");
    return memberDto;
  }
}
