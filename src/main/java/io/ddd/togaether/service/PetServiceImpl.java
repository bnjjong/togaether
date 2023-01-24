package io.ddd.togaether.service;

import io.ddd.togaether.dao.FollowLogRepository;
import io.ddd.togaether.dao.PetRepository;
import io.ddd.togaether.dto.PetCreationRequest;
import io.ddd.togaether.dto.PetDto;
import io.ddd.togaether.dto.mapper.PetMapper;
import io.ddd.togaether.dto.paging.CommonPagingResponse;
import io.ddd.togaether.dto.paging.PagingPetRequest;
import io.ddd.togaether.model.FollowLog;
import io.ddd.togaether.model.Member;
import io.ddd.togaether.model.Pet;
import io.ddd.togaether.util.FileHelper;
import jakarta.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * create on 2022/12/14. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetServiceImpl implements PetService {

  private final PetRepository petRepository;
  private final FollowLogRepository followLogRepository;

  private final PetMapper petMapper;


  @Value("${app.upload.dir}")
  private String uploadDir;

  @Transactional
  @Override
  public void create(PetCreationRequest request, MultipartFile image, Member owner)
      throws FileUploadException {

    Path uploadPath = Paths.get(
        uploadDir + File.separator
            + owner.getId() + File.separator
            + StringUtils.cleanPath(FileHelper.getFileNameServer(image)));

    // file upload
    try {
      File file = new File(uploadPath.toString());

      // *** 파일 업로드
      FileUtils.writeByteArrayToFile(file, image.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
      throw new FileUploadException("Could not store file : " + image.getOriginalFilename());
    }

    Pet pet = Pet.builder()
        .owner(owner)
        .name(request.getName())
        .species(request.getSpecies())
        .petCharacter(request.getPetCharacter())
        .gender(request.getGender())
        .birth(request.getBirth())
        .mainImage(uploadPath.toString())
        .description(request.getDescription())
        .etc(request.getEtc())
        .build();

    petRepository.save(pet);
  }

  @Override
  public InputStream retrievePetMainImage(Long petId) throws FileNotFoundException {
//    Member member = securityContextUtils.getLoginMember();
//    boolean matched = member.getPets().stream()
//        .anyMatch(p -> p.getId().equals(petId));

    Pet pet = petRepository.findById(petId)
        .orElseThrow(() -> new EntityNotFoundException("pet is not exits by id : " + petId));

    return new FileInputStream(pet.getMainImage());
  }

  @Override
  public CommonPagingResponse<PetDto> findPagingList(PagingPetRequest request) {
    Page<Pet> pets = petRepository.findAll(request.getPageable());
    return new CommonPagingResponse<>(
        pets.getTotalElements(),
        pets.getTotalPages(),
        pets.getNumber(),
        toDto(pets.getContent())
    );
  }



  @Override
  @Transactional
  public void addFollower(Long petId, Member memberBy) {
    Pet pet = petRepository.findById(petId)
        .orElseThrow(() -> new EntityNotFoundException("pet id is not found : " + petId));
    if (memberBy.getPets().contains(pet) ) {
      throw new IllegalStateException("it is not allowed to follow my pet.");
    }
    pet.addFollower(memberBy);

    // logging
    followLogRepository.save(
        FollowLog.builder()
            .memberId(memberBy.getId())
            .petId(pet.getId())
            .build()
    );
  }




  @Override
  public List<PetDto> toDto(List<Pet> pets) {
    return pets.stream()
        .map(p -> {
          PetDto petDto = petMapper.toDto(p);
          petDto.setMainImage("/pet/main-image/" + petDto.getId());
          return petDto;
        })
        .collect(Collectors.toList());
  }
}
