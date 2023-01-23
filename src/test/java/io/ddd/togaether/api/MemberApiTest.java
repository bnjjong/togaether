/*
 * Created By dogfootmaster@gmail.com on 2023
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2023/01/14
 */

package io.ddd.togaether.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestPartBody;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.ddd.togaether.config.CommonApiUnitTest;
import io.ddd.togaether.config.GlobalControllerExceptionHandler;
import io.ddd.togaether.config.security.SecurityContextUtils;
import io.ddd.togaether.dto.MemberDto;
import io.ddd.togaether.dto.PetDto;
import io.ddd.togaether.dto.SignupRequest;
import io.ddd.togaether.model.Member;
import io.ddd.togaether.service.MemberService;
import io.ddd.togaether.test.member.MemberFixture;
import io.ddd.togaether.test.pet.PetFixture;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

/**
 * create on 2023/01/14. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han
 * @version 1.0
 * @see
 * @since 1.0
 */
class MemberApiTest extends CommonApiUnitTest {
  private String baseUrl = "/member";

  @MockBean
  private MemberService memberService;
  @MockBean
  private SecurityContextUtils securityContextUtils;

  @Override
  protected Object getController() {
    return new MemberApi(memberService, securityContextUtils);
  }

  @Override
  protected Object getControllerAdvice() {
    return new GlobalControllerExceptionHandler();
  }

  @Nested
  class MemberCreate {
    @Test
    @DisplayName("회원 가입")
    void success() throws Exception {
      // given
      SignupRequest signupRequest = MemberFixture.memberForSignupTest();

      given(memberService.create(any())).willReturn(
          new MemberDto(999L,
              signupRequest.getEmail(),
              signupRequest.getName(),
              signupRequest.getBirth())
      );

      //when then
      mvc.perform(post(baseUrl)
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(signupRequest)))
          // then
          .andExpectAll(
              status().isCreated(),
              content().contentType(MediaType.APPLICATION_JSON)
          )
          .andDo(print());
    }
  }


  @Nested
  class MemberUploadProfilePicture {
    @Test
    @DisplayName("프로필 사진 업로드")
    void success() throws Exception {
      // given
      final File profilePicture = new File("./src/test/resources/test-files/profile.png");
      final MockMultipartFile mockFile = new MockMultipartFile("profile_picture",
          "profile.png",
          "image/png",
          new FileInputStream(profilePicture));

//      MockMultipartFile metadata = new MockMultipartFile("metadata", "",
//          "application/json", "{ \"version\": \"1.0\"}".getBytes());


      mvc.perform(
          ((MockMultipartHttpServletRequestBuilder)multipart(baseUrl+"/upload-picture").with(userToken()))
              .file(mockFile)
//              .file(metadata)
          )
          //then
          .andExpectAll(
              status().isOk()
          );
//          .andDo(document("member-upload-profile-picture/success",
//              requestParts(
//                partWithName("profile_picture")
//                    .description("the image file(png, jpeg...) to upload")
//              )
//          ));
    }
  }

  @Nested
  class MemberRetrieveMember {
    @Test
    @DisplayName("E-mail 로 회원 조회")
    void success() throws Exception {
      // given
      final SignupRequest signupRequest = MemberFixture.memberForSignup1();
      final String email = signupRequest.getEmail();

      given(memberService.findByEmail(email)).willReturn(
          new MemberDto(1L,
              signupRequest.getEmail(),
              signupRequest.getName(),
              signupRequest.getBirth()
          )
      );

      //when then
      mvc.perform(get(baseUrl+"/"+email)
              .contentType(MediaType.APPLICATION_JSON))
          // then
          .andExpectAll(
              status().isOk(),
              content().contentType(MediaType.APPLICATION_JSON)
          )
          .andDo(print());
    }
  }


  @Nested
  class MemberRetrieveProfilePicture {
    @Test
    @DisplayName("회원 프로필 사진 조회")
    void success() throws Exception {
      // given
      given(securityContextUtils.getLoginMember()).willReturn(
          objectMapper.convertValue(MemberFixture.memberForSignupTest(), Member.class)
      );
      given(memberService.retrieveMyProfilePictureInputStream(any())).willReturn(
          new FileInputStream("/Users/hanjongsang/github/togaether/build/resources/main/test/profile1.jpeg")
      );

      //when then
      mvc.perform(get(baseUrl+"/my-profile-picture").with(userToken())
              .contentType(MediaType.IMAGE_JPEG_VALUE))
          // then
          .andExpectAll(
              status().isOk(),
              content().contentType(MediaType.IMAGE_JPEG_VALUE)
          )
          .andDo(print());
    }
  }


  @Nested
  class MemberRetrieveAllMyPets {
    @Test
    @DisplayName("내가 등록한 펫 조회")
    void success() throws Exception {
      // given
      Member owner = objectMapper.convertValue(MemberFixture.memberForSignupTest(), Member.class);
      given(securityContextUtils.getLoginMember()).willReturn(
          owner
      );
      PetDto petDto = objectMapper.convertValue(PetFixture.retriever(owner), PetDto.class);
      petDto.setId(1L);
      given(memberService.findMyPets(any())).willReturn(
          List.of(petDto)
      );

      //when then
      mvc.perform(get(baseUrl+"/my-pets").with(userToken())
              .contentType(MediaType.APPLICATION_JSON))
          // then
          .andExpectAll(
              status().isOk(),
              content().contentType(MediaType.APPLICATION_JSON)
          )
          .andDo(print());
    }
  }

}