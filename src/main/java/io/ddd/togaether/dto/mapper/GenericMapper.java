/*
 * Copyright (C) 2018-2022 Bigin(https://bigin.io/main) - All Rights Reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 * Written by dev team, henry<jognsang@bigin.io>
 * Last modified on 2022/10/23
 */

package io.ddd.togaether.dto.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * create on 2022/10/23. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
public interface GenericMapper<D,E> {

  D toDto(E entity);
  E toEntity(D dto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  E updateFromDto(D dto, @MappingTarget E entity);

}
