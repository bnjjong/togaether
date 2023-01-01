/*
 * Created By dogfootmaster@gmail.com on 2023
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2023/01/01
 */

package io.ddd.togaether.dto.mapper;

import io.ddd.togaether.dto.PetDto;
import io.ddd.togaether.model.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * create on 2023/01/01. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han
 * @version 1.0
 * @see
 * @since 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PetMapper extends GenericMapper<PetDto, Pet>{

}
