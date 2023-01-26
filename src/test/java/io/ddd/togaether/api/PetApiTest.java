/*
 * Created By dogfootmaster@gmail.com on 2023
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2023/01/24
 */

package io.ddd.togaether.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.ddd.togaether.config.CommonApiUnitTest;
import io.ddd.togaether.config.GlobalControllerExceptionHandler;
import io.ddd.togaether.config.security.SecurityContextUtils;
import io.ddd.togaether.dto.paging.CommonPagingResponse;
import io.ddd.togaether.dto.paging.PagingPetRequest;
import io.ddd.togaether.dto.paging.PetOrderBy;
import io.ddd.togaether.model.Member;
import io.ddd.togaether.service.PetService;
import io.ddd.togaether.test.member.MemberFixture;
import io.ddd.togaether.test.pet.PetFixture;
import io.ddd.togaether.util.FileHelper;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

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
class PetApiTest extends CommonApiUnitTest {
  private String baseUrl = "/pet";

  @MockBean
  private PetService petService;

  @MockBean
  private SecurityContextUtils securityContextUtils;

  @Override
  protected Object getController() {
    return new PetApi(petService, securityContextUtils);
  }

  @Override
  protected Object getControllerAdvice() {
    return new GlobalControllerExceptionHandler();
  }

  @Nested
  class PetCreate {
    @Test
    @DisplayName("펫 등록")
    void success() throws Exception {
      // given
      String requestBody = objectMapper.writeValueAsString(PetFixture.retrieverForCreation());
      final MockMultipartFile petUpload = new MockMultipartFile("pet_upload",
          requestBody,
          "application/json", requestBody.getBytes(StandardCharsets.UTF_8));
      final File petPicture = new File("./src/test/resources/test-files/Retriever.png");
      final MockMultipartFile mockFile = new MockMultipartFile(
          "main_image",
          "Retriever.png",
          "image/png",
          new FileInputStream(petPicture));

      //when then
      mvc.perform(
          ((MockMultipartHttpServletRequestBuilder)multipart(baseUrl)
              .with(userToken()))
              .file(mockFile)
              .file(petUpload)
          )
          // then
          .andExpectAll(
              status().isCreated()
          )
          .andDo(print());
    }
  }

  @Nested
  class PetRetrieveList {

    @Test
    @DisplayName("펫 페이징 조회")
    void success() throws Exception {
      // given
      PagingPetRequest request = new PagingPetRequest(10, 1,
          PetOrderBy.FOLLOWER_COUNT_DESC);

      given(petService.findPagingList(any())).willReturn(
          new CommonPagingResponse<>(
              1,1,0,
              List.of(PetFixture.retrieverDto())
          )
      );

      // when then
      mvc.perform(get(baseUrl+"/list")
              .content(objectMapper.writeValueAsString(request))
              .contentType(MediaType.APPLICATION_JSON))
          // then
          .andExpectAll(
              status().isOk()
          )
          .andDo(print());
    }
  }

  @Nested
  class PetFollow {

    @Test
    @DisplayName("펫 팔로우")
    void success() throws Exception {
      // given
      Long petId = 1L;
      given(securityContextUtils.getLoginMember()).willReturn(
          objectMapper.convertValue(MemberFixture.memberForSignupTest(), Member.class)
      );

      // when then
      mvc.perform(put(baseUrl+"/"+petId+"/follow")
              .with(userToken())
              .contentType(MediaType.APPLICATION_JSON)
          )
          // then
          .andExpectAll(
              status().isOk()
          )
          .andDo(print());
    }
  }


  @Nested
  class PetRetrieveMainImage {
    @Test
    @DisplayName("펫 메인 이미지 조회")
    void success() throws Exception {
      // given
      Long petId = 1L;
      given(petService.retrievePetMainImagePath(any())).willReturn(
          FileHelper.getFileFromResource("test/Retriever.png").toString()
      );

      //when then
      mvc.perform(get(baseUrl+"/main-image/"+petId)
//              .contentType(MediaType.IMAGE_PNG_VALUE)
              )
          // then
          .andExpectAll(
              status().isOk(),
              content().contentType(MediaType.IMAGE_PNG_VALUE)
          )
          .andDo(print());
    }
  }


}