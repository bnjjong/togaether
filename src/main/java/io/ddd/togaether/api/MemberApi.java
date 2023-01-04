package io.ddd.togaether.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.ddd.togaether.dto.MemberDto;
import io.ddd.togaether.dto.PetDto;
import io.ddd.togaether.dto.SignupRequest;
import io.ddd.togaether.service.MemberService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * create on 2022/12/11. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han(Henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApi {
  private final MemberService memberService;


  /**
   * 회원 가입
   * @param request
   * @return
   */
  @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
  public String create(@Valid @RequestBody SignupRequest request) {
    MemberDto memberDto = memberService.create(request);
    return "created member, " + memberDto;
  }

  @PostMapping(value = "/upload-picture",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<Void> uploadProfilePicture(
      @RequestParam final MultipartFile profile) throws FileUploadException {
    memberService.uploadProfilePicture(profile);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping(value = "/{email}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<MemberDto> retrieveMember(@PathVariable(value = "email") final String email) {
    MemberDto dto = memberService.findByEmail(email);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }


  @GetMapping(
      value = "/my-profile-picture",
      produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}
  )
  public byte[] retrieveProfile() throws IOException {
    try (InputStream in = memberService.retrieveMyProfilePictureInputStream()) {
      return IOUtils.toByteArray(in);
    }
  }

  @GetMapping(value = "/my-pets")
  public ResponseEntity<List<PetDto>> findAll() {
    List<PetDto> pets = memberService.findMyPets();
    return new ResponseEntity<>(pets, HttpStatus.OK);
  }

}
