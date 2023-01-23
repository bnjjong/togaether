/*
 * Created By dogfootmaster@gmail.com on 2023
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2023/01/14
 */

package io.ddd.togaether.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.ddd.togaether.config.CommonApiUnitTest;
import io.ddd.togaether.config.GlobalControllerExceptionHandler;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

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
class CommonApiTest extends CommonApiUnitTest {
  private String baseUrl = "/common";


  @Override
  protected Object getController() {
    return new CommonApi();
  }

  @Override
  protected Object getControllerAdvice() {
    return new GlobalControllerExceptionHandler();
  }

  @Nested
  class CommonRetrieveAllSpecies {
    @Test
    @DisplayName("모든 강아지 종 조회")
    void success() throws Exception {
      //when then
      mvc.perform(get(baseUrl + "/species-all")
              .contentType(MediaType.APPLICATION_JSON))
          .andExpectAll(
              status().isOk(),
              content().contentType(MediaType.APPLICATION_JSON)
          )
          .andDo(print());
    }
  }

  @Nested
  class CommonRetrieveSpecies {
    @Test
    @DisplayName("강아지 종 검색")
    void success() throws Exception {
      final String keyword = "리트";
      //when then
      mvc.perform(get(baseUrl + "/species/"+keyword)
              .contentType(MediaType.APPLICATION_JSON))
          .andExpectAll(
              status().isOk(),
              content().contentType(MediaType.APPLICATION_JSON)
          )
//          .andDo(document("/retrieve-species",
//                requestFields(fieldWithPath("keyword").description("support only kor_name, index_code."))
//              )
//          )
          .andDo(print());
    }
  }


  @Nested
  class CommonRetrieveAllCharacters {
    @Test
    @DisplayName("모든 성격 조회")
    void success() throws Exception {

      //when then
      mvc.perform(get(baseUrl + "/characters-all")
              .contentType(MediaType.APPLICATION_JSON))
          .andExpectAll(
              status().isOk(),
              content().contentType(MediaType.APPLICATION_JSON)
          )
          .andDo(print());
    }
  }
}