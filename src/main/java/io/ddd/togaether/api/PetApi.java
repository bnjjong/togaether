package io.ddd.togaether.api;

import io.ddd.togaether.config.security.SecurityContextUtils;
import io.ddd.togaether.dto.ContentCreationRequest;
import io.ddd.togaether.dto.ContentResponse;
import io.ddd.togaether.dto.PetCreationRequest;
import io.ddd.togaether.dto.PetDto;
import io.ddd.togaether.dto.paging.CommonPagingResponse;
import io.ddd.togaether.dto.paging.PagingCommonImplRequest;
import io.ddd.togaether.dto.paging.PagingPetRequest;
import io.ddd.togaether.model.Member;
import io.ddd.togaether.service.PetService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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

@RestController
@RequestMapping("/pet")
@Slf4j
@RequiredArgsConstructor
public class PetApi {

  private final PetService petService;
  private final SecurityContextUtils securityContextUtils;

  /**
   * <p> 펫을 생성 한다.</p>
   *
   * @param request 생성할 펫 요청 정보.
   * @param image 메인 이미지 파일.
   * @return {@code ResponseEntity}.
   * @throws FileUploadException
   */
  @PostMapping(value = "/with-image",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> create(
      @RequestPart("pet_upload") @Valid PetCreationRequest request,
      @RequestPart("main_image") MultipartFile image) throws FileUploadException {
    Member loginMember = securityContextUtils.getLoginMember();
    petService.create(request, image, loginMember);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping(value = "")
  public ResponseEntity<Void> create(
          @Valid @RequestBody PetCreationRequest request) throws FileUploadException {
    Member loginMember = securityContextUtils.getLoginMember();
    petService.create(request, loginMember);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }



  @PutMapping(value = "/{petId}")
  public ResponseEntity<Void> update(
          @PathVariable(value = "petId") final Long petId,
          @Valid @RequestBody PetCreationRequest request) {
    Member loginMember = securityContextUtils.getLoginMember();
    petService.update(loginMember, petId, request);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping(value = "/{petId}/main-image")
  public ResponseEntity<Void> updateMainImage(
          @PathVariable(value = "petId") final Long petId,
          @RequestPart("main_image") MultipartFile image
  ) throws FileUploadException {
    Member loginMember = securityContextUtils.getLoginMember();
    petService.updateMainImage(loginMember, petId, image);

    return new ResponseEntity<>(HttpStatus.OK);
  }



  @GetMapping(value = "/list")
  public ResponseEntity<CommonPagingResponse<PetDto>> retrieveList(
      @Valid @RequestBody PagingPetRequest request
  ) {
    CommonPagingResponse<PetDto> pets = petService.findPagingList(request);
    return new ResponseEntity<>(pets, HttpStatus.OK);
  }

  @GetMapping(value = "/my-following")
  @Transactional
  public ResponseEntity<Map<String,List<PetDto>>> retrieveFollowing(
  ) {
    Member member = securityContextUtils.getLoginMember();
    List<PetDto> petDtos = petService.retrieveFollowing(member);
    Map<String, List<PetDto>> result = new HashMap<>();
    result.put("rows", petDtos);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }



  /**
   * <p> 해당 펫을 팔로우 한다. </p>
   * @param petId 팔로우 할 펫 아이디.
   * @return {@code ResponseEntity}.
   */
  @PutMapping(value = "/{petId}/follow")
  public ResponseEntity<Void> follow(
      @PathVariable(value = "petId") final Long petId
  ) {
    Member loginMember = securityContextUtils.getLoginMember();

    petService.addFollower(petId, loginMember);
    return new ResponseEntity<>(HttpStatus.OK);
  }


  /**
   * <p> 펫 메인 이미지를 조회 한다.</p>
   *
   * @param petId 펫 아이디
   * @return {@code ResponseEntity} with image resource.
   * @throws IOException
   */
  @GetMapping(
      value = "/main-image/{petId}",
      produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}
  )
  public ResponseEntity<Resource> retrieveMainImage(
      @PathVariable(value = "petId") final Long petId
  ) throws IOException {

    String imagePath = petService.retrievePetMainImagePath(petId);
    MediaType mediaType = MediaType.parseMediaType(Files.probeContentType(Paths.get(imagePath)));
    UrlResource resource = new UrlResource("file:" + imagePath);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_TYPE, mediaType.toString())
        .body(resource);
  }


  @PostMapping(value = "/{petId}/content",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> createContent(
      @PathVariable(value = "petId") final Long petId,
      @RequestPart("content") @Valid ContentCreationRequest request,
      @RequestPart("image") MultipartFile image
  ) throws FileUploadException {
    petService.createContent(petId, request, image);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping(value = "/{petId}/contents")
  public ResponseEntity<List<ContentResponse>> retrieveContents(
      @PathVariable(value = "petId") final Long petId
  ) {
    List<ContentResponse> contents = petService.findContents(petId);
    return new ResponseEntity<>(contents, HttpStatus.OK);
  }

  @GetMapping(
      value = "{contentId}/content-image",
      produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}
  )
  public ResponseEntity<Resource> retrieveContentImage(
      @PathVariable(value = "contentId") final Long contentId
  ) throws IOException {

    String imagePath = petService.retrieveContentImagePath(contentId);
    MediaType mediaType = MediaType.parseMediaType(Files.probeContentType(Paths.get(imagePath)));
    UrlResource resource = new UrlResource("file:" + imagePath);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_TYPE, mediaType.toString())
        .body(resource);
  }




}
