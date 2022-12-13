package io.ddd.togaether.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.ddd.togaether.dto.MemberDto;
import io.ddd.togaether.dto.SignupRequest;
import io.ddd.togaether.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
