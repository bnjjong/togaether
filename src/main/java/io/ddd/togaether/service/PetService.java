package io.ddd.togaether.service;

import io.ddd.togaether.config.security.SecurityContextUtils;
import io.ddd.togaether.dao.PetRepository;
import io.ddd.togaether.dto.PetCreationRequest;
import io.ddd.togaether.model.Member;
import io.ddd.togaether.model.Pet;
import io.ddd.togaether.util.FileHelper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetService {

  private final PetRepository repository;

  private final SecurityContextUtils securityContextUtils;

  @Value("${app.upload.dir:${user.home}}")
  private String uploadDir;

  @Transactional
  public void create(PetCreationRequest request, MultipartFile image)
      throws FileUploadException {
    Member member = securityContextUtils.getLoginMember();

    Path uploadPath = Paths.get(
        uploadDir + File.separator
            + member.getId() + File.separator
            + StringUtils.cleanPath(image.getOriginalFilename()));

    // file upload
    try {
      String fileName = getFileNameServer(image);
      File file = new File(uploadPath.toString());

      // *** 파일 업로드
      FileUtils.writeByteArrayToFile(file, image.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
      throw new FileUploadException("Could not store file : " + image.getOriginalFilename());
    }

    Pet pet = Pet.builder()
        .owner(member)
        .name(request.getName())
        .species(request.getSpecies())
        .petCharacter(request.getPetCharacter())
        .gender(request.getGender())
        .birth(request.getBirth())
        .mainImagePath(uploadPath.toString())
        .build();

    repository.save(pet);
  }

  private static String getFileNameServer(MultipartFile multipartFile) {
    // 파일 확장자 추출
    int pos = multipartFile.getOriginalFilename().lastIndexOf(".");
    String ext = multipartFile.getOriginalFilename().substring(pos + 1);

    // 서버에 올라갈 파일명 반환
    return makeFileName() + "." + ext;
  }

  // 파일명 랜덤 생성
  public static String makeFileName() {
    Date now = new Date();
    String today = new SimpleDateFormat("yyyyMMddHHmmss").format(now);

    String random = "";
    for (int i = 1; i <= 10; i++) {
      char ch = (char) ((Math.random() * 26) + 97);
      random += ch;
    }
    String result = today + random;

    return result;
  }

}
