package io.ddd.togaether.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.ddd.togaether.dto.MemberDto;
import io.ddd.togaether.dto.PetCreationRequest;
import io.ddd.togaether.dto.PetDto;
import io.ddd.togaether.dto.SignupRequest;
import io.ddd.togaether.service.PetService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
