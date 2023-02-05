package io.ddd.togaether.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.ddd.togaether.config.security.SecurityContextUtils;
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

  private final SecurityContextUtils securityContextUtils;


  /**
   * 회원 가입 요청.
   *
   * @param request signup request dto.
   * @return @return {@code ResponseEntity}
   */
  @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<MemberDto> create(@Valid @RequestBody SignupRequest request) {
    MemberDto memberDto = memberService.create(request);
    System.out.println(memberDto);
    return new ResponseEntity<>(memberDto, HttpStatus.CREATED);
  }

  /**
   * 프로필 사진 업로드 요청.
   *
   * @param profilePicture 프로필 사진.
   * @return {@code ResponseEntity}
   * @throws FileUploadException 파일 업로드 실패 시
   */
  @PostMapping(value = "/upload-picture",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<Void> uploadProfilePicture(
      @RequestParam(name = "profile_picture") final MultipartFile profilePicture) throws FileUploadException {
    memberService.uploadProfilePicture(securityContextUtils.getLoginMember(), profilePicture);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * <p>E-mail 로 회원 조회.</p>
   *
   * @param email 조회할 E-mail
   * @return {@code ResponseEntity}
   */
  @GetMapping(value = "/{email}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<MemberDto> retrieveMember(@PathVariable(value = "email") final String email) {
    MemberDto dto = memberService.findByEmail(email);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }


  /**
   * <p> 내 프로필 사진 조회 </p>
   *
   * @return {@code byte} image file byte.
   * @throws IOException
   */
  @GetMapping(
      value = "/my-profile-picture",
      produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}
  )
  public byte[] retrieveProfilePicture() throws IOException {
    try (InputStream in = memberService.retrieveMyProfilePictureInputStream(securityContextUtils.getLoginMember())) {
      return IOUtils.toByteArray(in);
    }
  }

  /**
   * <p> 내가 등록한 펫(강아지) 조회 </p>
   *
   * @return {@code ResponseEntity}
   */
  @GetMapping(value = "/my-pets")
  public ResponseEntity<List<PetDto>> retrieveAllMyPets() {
    List<PetDto> pets = memberService.findMyPets(securityContextUtils.getLoginMember());
    return new ResponseEntity<>(pets, HttpStatus.OK);
  }

  /**
   * <p> 회원 탈퇴 한다. </p>
   *
   * @return
   */
  @PostMapping(value="/withdraw")
  public ResponseEntity<Void> withdraw() {
    memberService.withdraw(securityContextUtils.getLoginMember());
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
