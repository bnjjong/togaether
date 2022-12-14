package io.ddd.togaether.api;

import io.ddd.togaether.dto.PetDto;
import io.ddd.togaether.service.PetService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("")
  public ResponseEntity<List<PetDto>> retrievePets() {
    return null;
  }
}
