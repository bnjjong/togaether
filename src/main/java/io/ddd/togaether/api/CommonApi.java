package io.ddd.togaether.api;

import io.ddd.togaether.dto.CharacterDto;
import io.ddd.togaether.dto.SpeciesDto;
import io.ddd.togaether.model.Character;
import io.ddd.togaether.model.Species;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create on 2022/12/22. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
@RestController
@RequestMapping("/common")
@Slf4j
@RequiredArgsConstructor
public class CommonApi {

  /**
   * <p> 모든 강아지 종을 조회 한다. </p>
   *
   * @return {@code ResponseEntity}
   */
  @GetMapping("/species-all")
  public ResponseEntity<List<SpeciesDto>> retrieveAllSpecies() {
    List<SpeciesDto> speciesDtos = Species.getAll().stream()
        .map(s -> new SpeciesDto(s.toString(), s.getKorName(), s.getIndexCode()))
        .collect(Collectors.toList());

    return new ResponseEntity<>(speciesDtos, HttpStatus.OK);
  }

  /**
   * <p> 강아지 종을 조회 한다. </p>
   *
   * @param keyword support only kor_name, index_code.
   * @return {@code ResponseEntity}
   */
  @GetMapping("/species/{keyword}")
  public ResponseEntity<List<SpeciesDto>> retrieveSpecies(
      @PathVariable(value = "keyword" ) final String keyword
  ) {
    List<SpeciesDto> speciesDtos = Species.getAll().stream()
        .filter(s -> s.getKorName().contains(keyword) || s.getIndexCode().equals(keyword))
        .map(s -> new SpeciesDto(s.toString(), s.getKorName(), s.getIndexCode()))
        .collect(Collectors.toList());

    return new ResponseEntity<>(speciesDtos, HttpStatus.OK);
  }


  /**
   * <p> 모든 강아지 성격을 조회 한다. </p>
   *
   * @return {@code ResponseEntity}
   */
  @GetMapping("/characters-all")
  public ResponseEntity<List<CharacterDto>> retrieveAllCharacters() {
    List<CharacterDto> characters = Character.getAll().stream()
        .map(c -> new CharacterDto(c.toString(), c.getKorText()))
        .collect(Collectors.toList());

    return new ResponseEntity<>(characters, HttpStatus.OK);
  }

}
