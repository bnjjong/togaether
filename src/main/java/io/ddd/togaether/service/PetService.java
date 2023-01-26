/*
 * Created By dogfootmaster@gmail.com on 2023
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2023/01/24
 */

package io.ddd.togaether.service;

import io.ddd.togaether.dto.PetCreationRequest;
import io.ddd.togaether.dto.PetDto;
import io.ddd.togaether.dto.paging.CommonPagingResponse;
import io.ddd.togaether.dto.paging.PagingPetRequest;
import io.ddd.togaether.model.Member;
import io.ddd.togaether.model.Pet;
import java.io.IOException;
import java.util.List;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

/**
 * create on 2023/01/24. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han
 * @version 1.0
 * @see
 * @since 1.0
 */
public interface PetService {
  void create(PetCreationRequest request, MultipartFile image, Member owner)
      throws FileUploadException;

  String retrievePetMainImagePath(Long petId) throws IOException;
  CommonPagingResponse<PetDto> findPagingList(PagingPetRequest request);

  void addFollower(Long petId, Member memberBy);
  List<PetDto> toDto(List<Pet> pets);

}
