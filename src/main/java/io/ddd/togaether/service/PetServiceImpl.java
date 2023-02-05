package io.ddd.togaether.service;

 import io.ddd.togaether.dao.ContentRepository;
import io.ddd.togaether.dao.FollowLogRepository;
import io.ddd.togaether.dao.PetRepository;
import io.ddd.togaether.dto.ContentCreationRequest;
import io.ddd.togaether.dto.ContentResponse;
import io.ddd.togaether.dto.PetCreationRequest;
import io.ddd.togaether.dto.PetDto;
import io.ddd.togaether.dto.mapper.PetMapper;
import io.ddd.togaether.dto.paging.CommonPagingResponse;
import io.ddd.togaether.dto.paging.PagingPetRequest;
import io.ddd.togaether.model.Content;
import io.ddd.togaether.model.FollowLog;
import io.ddd.togaether.model.Member;
import io.ddd.togaether.model.Pet;
import io.ddd.togaether.util.FileHelper;
import jakarta.persistence.EntityNotFoundException;
import java.io.File;
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
  private final ContentRepository contentRepository;
  private final FollowLogRepository followLogRepository;

  private final PetMapper petMapper;


  @Value("${app.upload.dir}")
  private String uploadDir;

  @Transactional
  @Override
  public void create(PetCreationRequest request, MultipartFile image, Member owner)
      throws FileUploadException {

    // TODO: 2023/01/30 리팩토링 대상.
    Path uploadPath = Paths.get(
        uploadDir + File.separator
            + owner.getId() + File.separator
            + StringUtils.cleanPath(FileHelper.getFileNameServer(image)));

    // file upload
    fileUpload(image, uploadPath);

    Pet pet = create(request, owner);
    pet.updateMainImage(uploadPath.toString());
  }

  @Transactional
  @Override
  public Pet create(PetCreationRequest request, Member owner) {
    Pet pet = Pet.builder()
            .owner(owner)
            .name(request.getName())
            .species(request.getSpecies())
            .petCharacter(request.getPetCharacter())
            .gender(request.getGender())
            .birth(request.getBirth())
            .description(request.getDescription())
            .etc(request.getEtc())
            .build();

    return petRepository.save(pet);
  }

  @Transactional
  @Override
  public void update(Member loginMember, Long petId, PetCreationRequest request) {
    // TODO validation pets

    Pet pet = petRepository.findById(petId)
            .orElseThrow(() -> new EntityNotFoundException("pet id is not found : " + petId));

    pet.update(
            request.getName(),
            request.getSpecies(),
            request.getPetCharacter(),
            request.getGender(),
            request.getBirth(),
            request.getDescription(),
            request.getEtc()
            );
  }

  @Transactional
  @Override
  public void updateMainImage(Member owner, Long petId, MultipartFile image) throws FileUploadException {
    // TODO validation pets

    Pet pet = petRepository.findById(petId)
            .orElseThrow(() -> new EntityNotFoundException("pet id is not found : " + petId));
    Path uploadPath = Paths.get(
            uploadDir + File.separator
                    + owner.getId() + File.separator
                    + StringUtils.cleanPath(FileHelper.getFileNameServer(image)));

    // file upload
    fileUpload(image, uploadPath);
    pet.updateMainImage(uploadPath.toString());

  }




  @Override
  public String retrievePetMainImagePath(Long petId) {
    Pet pet = petRepository.findById(petId)
        .orElseThrow(() -> new EntityNotFoundException("pet is not exits by id : " + petId));

    return pet.getMainImage();
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

  @Transactional
  @Override
  public void createContent(Long petId, ContentCreationRequest request, MultipartFile image)
      throws FileUploadException {
    Pet pet = petRepository.findById(petId)
        .orElseThrow(() -> new EntityNotFoundException("pet id is not found : " + petId));

    Path uploadPath = Paths.get(
        uploadDir + File.separator
            + "pet_"+ pet.getId() + File.separator
            + StringUtils.cleanPath(FileHelper.getFileNameServer(image)));

    // file upload
    fileUpload(image, uploadPath);

    pet.addContent(request.getContent(), uploadPath.toString());
  }

  @Override
  public List<ContentResponse> findContents(Long petId) {
    Pet pet = petRepository.findById(petId)
        .orElseThrow(() -> new EntityNotFoundException("pet id is not found : " + petId));
    return pet.getPetContent().stream()
        .map(c -> new ContentResponse(
            c.getContent(),
            "/pet/" + c.getId() + "/content-image")
        )
        .collect(Collectors.toList());
  }

  @Override
  public String retrieveContentImagePath(Long contentId) {
    Content content = contentRepository.findById(contentId)
        .orElseThrow(() -> new EntityNotFoundException("content is not exits by id : " + contentId));

    return content.getImagePath();
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

  private void fileUpload(MultipartFile image, Path uploadPath) throws FileUploadException {
    try {
      File file = new File(uploadPath.toString());

      // *** 파일 업로드
      FileUtils.writeByteArrayToFile(file, image.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
      throw new FileUploadException("Could not store file : " + image.getOriginalFilename());
    }
  }


}
