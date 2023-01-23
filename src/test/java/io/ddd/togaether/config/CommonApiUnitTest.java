/*
 * Created By dogfootmaster@gmail.com on 2023
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2023/01/13
 */

package io.ddd.togaether.config;

import static capital.scalable.restdocs.misc.AuthorizationSnippet.documentAuthorization;

import capital.scalable.restdocs.AutoDocumentation;
import capital.scalable.restdocs.SnippetRegistry;
import capital.scalable.restdocs.jackson.JacksonResultHandlers;
import capital.scalable.restdocs.response.ResponseModifyingPreprocessors;
import capital.scalable.restdocs.section.SectionBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.cli.CliDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer;
import org.springframework.restdocs.mockmvc.MockMvcSnippetConfigurer;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.OperationPreprocessor;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.restdocs.templates.TemplateFormats;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * create on 2023/01/13. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han
 * @version 1.0
 * @see
 * @since 1.0
 */
@ActiveProfiles({"test"})
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@Disabled
public abstract class CommonApiUnitTest {
  protected static final Logger log = LoggerFactory.getLogger(CommonApiUnitTest.class);
  protected MockMvc mvc;
  protected ObjectMapper objectMapper;

  public CommonApiUnitTest() {
  }

  @BeforeEach
  public void setup(RestDocumentationContextProvider provider) {
    this.objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    this.objectMapper.registerModule(new JavaTimeModule());


    this.mvc = this.initMockMvc(this.getController(), provider, defaultDocument());
  }

  protected RestDocumentationResultHandler defaultDocument() {
    // identifier expression https://docs.spring.io/spring-restdocs/docs/2.0.5.RELEASE/reference/html5/#documentating-your-api-parameterized-output-directories
    return MockMvcRestDocumentation
        .document(
            "{class-name}/{method-name}",
            Preprocessors.preprocessRequest(new OperationPreprocessor[]{
                Preprocessors.prettyPrint()}),
            Preprocessors.preprocessResponse(new OperationPreprocessor[]{
                ResponseModifyingPreprocessors.replaceBinaryContent(),
                ResponseModifyingPreprocessors.limitJsonArrayLength(this.objectMapper),
                Preprocessors.prettyPrint()}),
            new Snippet[]{
              AutoDocumentation
                  .sectionBuilder()
                  .snippetNames(
                      SnippetRegistry.AUTO_AUTHORIZATION,
                      SnippetRegistry.AUTO_PATH_PARAMETERS,
                      SnippetRegistry.AUTO_REQUEST_PARAMETERS,
                      SnippetRegistry.AUTO_MODELATTRIBUTE,
                      SnippetRegistry.AUTO_REQUEST_FIELDS,
                      SnippetRegistry.AUTO_RESPONSE_FIELDS,
                      SnippetRegistry.AUTO_LINKS,
                      SnippetRegistry.AUTO_EMBEDDED,
                      SnippetRegistry.CURL_REQUEST,
                      SnippetRegistry.HTTP_RESPONSE
                  )
                  .skipEmpty(true)
                  .build()}
        );
  }

  protected abstract Object getController();

  protected abstract Object getControllerAdvice();

  private MockMvc initMockMvc(Object controller, RestDocumentationContextProvider provider, RestDocumentationResultHandler restDocs) {
    return ((StandaloneMockMvcBuilder)((StandaloneMockMvcBuilder)((StandaloneMockMvcBuilder) MockMvcBuilders.standaloneSetup(new Object[]{controller}).setControllerAdvice(new Object[]{this.getControllerAdvice()}).apply((MockMvcConfigurer)((MockMvcSnippetConfigurer)((MockMvcSnippetConfigurer)((MockMvcRestDocumentationConfigurer)MockMvcRestDocumentation.documentationConfiguration(provider).uris().withScheme("http").withHost("localhost").withPort(8080).and()).snippets().withEncoding("UTF-8")).withTemplateFormat(
        TemplateFormats.asciidoctor())).withDefaults(new Snippet[]{CliDocumentation.curlRequest(), HttpDocumentation.httpRequest(), HttpDocumentation.httpResponse(), AutoDocumentation.requestFields(), AutoDocumentation.responseFields(), AutoDocumentation.pathParameters(), AutoDocumentation.requestParameters(), AutoDocumentation.description(), AutoDocumentation.methodAndPath(), AutoDocumentation.section(), AutoDocumentation.links(), AutoDocumentation.embedded(), AutoDocumentation.modelAttribute((new RequestMappingHandlerAdapter()).getArgumentResolvers())}))).alwaysDo(
        JacksonResultHandlers.prepareJackson(this.objectMapper))).alwaysDo(restDocs)).build();
  }

  protected JSONObject toJsonObject(Object o) throws JsonProcessingException {
    String requestStr = this.objectMapper.writeValueAsString(o);
    JSONObject json = JSONObject.fromObject(requestStr);
    log.info("to json object : {}", json);
    return json;
  }

  protected RequestPostProcessor userToken() {
    return new RequestPostProcessor() {
      @Override
      public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
        final String accessToken = "{{token}}";
        request.addHeader("Authorization", "Bearer " + accessToken);
        return documentAuthorization(request, "User access token required.");
      }
    };
  }

}
