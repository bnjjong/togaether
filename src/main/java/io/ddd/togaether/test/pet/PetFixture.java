/*
 * Created By dogfootmaster@gmail.com on 2023
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2023/01/09
 */

package io.ddd.togaether.test.pet;

import io.ddd.togaether.model.Character;
import io.ddd.togaether.model.Gender;
import io.ddd.togaether.model.Member;
import io.ddd.togaether.model.Pet;
import io.ddd.togaether.model.Species;
import io.ddd.togaether.util.FileHelper;
import io.ddd.togaether.util.FileReadException;
import java.time.LocalDate;

/**
 * create on 2023/01/09. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han
 * @version 1.0
 * @see
 * @since 1.0
 */
public class PetFixture {

  public static Pet retriever(Member owner) throws FileReadException {
    return Pet.builder()
        .owner(owner)
        .name("댕댕이")
        .species(Species.GOLDEN_RETRIEVER)
        .petCharacter(Character.ENERGETIC)
        .gender(Gender.MALE)
        .birth(LocalDate.of(2020,1,20))
        .mainImage(FileHelper.getFileFromResource("test/Retriever.png").toString())
        .description("매우 온순하지만 활력이 넘쳐요!")
        .etc("")
        .build();
  }


  public static Pet greyHound(Member owner) throws FileReadException {
    return Pet.builder()
        .owner(owner)
        .name("그레이")
        .species(Species.GREY_HOUND)
        .petCharacter(Character.LITTLE_TIMID)
        .gender(Gender.FEMALE)
        .birth(LocalDate.of(2019,5,20))
        .mainImage(FileHelper.getFileFromResource("test/GreyHound.jpeg").toString())
        .description("조용하지만 매우 친절해요.")
        .etc("")
        .build();
  }

  public static Pet samoyed(Member owner) throws FileReadException {
    return Pet.builder()
        .owner(owner)
        .name("흰둥이")
        .species(Species.SAMOYED_DOG)
        .petCharacter(Character.SOCIABLE)
        .gender(Gender.FEMALE)
        .birth(LocalDate.of(2016,10,30))
        .mainImage(FileHelper.getFileFromResource("test/Samoyed.jpeg").toString())
        .description("친한척 심해요.")
        .etc("")
        .build();
  }

}
