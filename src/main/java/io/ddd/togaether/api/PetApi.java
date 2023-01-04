package io.ddd.togaether.api;

import io.ddd.togaether.dto.PetCreationRequest;
import io.ddd.togaether.dto.PetDto;
import io.ddd.togaether.dto.paging.CommonPagingResponse;
import io.ddd.togaether.dto.paging.PagingCommonImplRequest;
import io.ddd.togaether.service.PetService;
import jakarta.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

  @PostMapping(value = "",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> create(
      @RequestPart("pet_upload") @Valid PetCreationRequest request,
      @RequestPart("main_image") MultipartFile image) throws FileUploadException {
    petService.create(request, image);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping(value = "/list")
  public ResponseEntity<CommonPagingResponse<PetDto>> findList(
      @Valid @RequestBody PagingCommonImplRequest request
  ) {
    CommonPagingResponse<PetDto> pets = petService.findPagingList(request);
    return new ResponseEntity<>(pets, HttpStatus.OK);
  }

  @GetMapping(
      value = "/image",
      produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}
  )
  public byte[] getImage() throws IOException {
    try (InputStream in = new FileInputStream("/Users/henry/images/1/retriever.png")) {
      return IOUtils.toByteArray(in);
    }
  }


  @GetMapping(
      value = "/main-image/{petId}",
      produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}
  )
  public byte[] getmainImage(
      @PathVariable(value = "petId") final Long petId
  ) throws IOException {
    try (InputStream in = petService.retrievePetMainImage(petId)) {
      return IOUtils.toByteArray(in);
    }
  }
}
