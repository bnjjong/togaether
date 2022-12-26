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

  @Value("${app.upload.dir}")
  private String uploadDir;

  @Transactional
  public void create(PetCreationRequest request, MultipartFile image)
      throws FileUploadException {
    Member member = securityContextUtils.getLoginMember();

    Path uploadPath = Paths.get(
        uploadDir + File.separator
            + member.getId() + File.separator
            + StringUtils.cleanPath(FileHelper.getFileNameServer(image)));

    // file upload
    try {
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
        .description(request.getDescription())
        .etc(request.getEtc())
        .build();

    repository.save(pet);
  }





}
