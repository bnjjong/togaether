package io.ddd.togaether.dto.mapper;

import io.ddd.togaether.dto.MemberDto;
import io.ddd.togaether.model.Member;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

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
@Mapper(componentModel = "spring")
public interface MemberMapper extends GenericMapper<MemberDto, Member> {

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Override
  Member updateFromDto(MemberDto dto, Member entity);
}
