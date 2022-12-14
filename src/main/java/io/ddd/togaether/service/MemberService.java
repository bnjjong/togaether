package io.ddd.togaether.service;

import io.ddd.togaether.config.security.SecurityContextUtils;
import io.ddd.togaether.dao.MemberRepository;
import io.ddd.togaether.dto.MemberDto;
import io.ddd.togaether.dto.PetDto;
import io.ddd.togaether.dto.SignupRequest;
import io.ddd.togaether.dto.mapper.MemberMapper;
import io.ddd.togaether.dto.mapper.PetMapper;
import io.ddd.togaether.model.AuthGrade;
import io.ddd.togaether.model.Member;
import io.ddd.togaether.model.Pet;
import io.ddd.togaether.util.FileHelper;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository repository;
  private final PetService petService;

  private final MemberMapper mapper;
  private final PasswordEncoder passwordEncoder;
  private final SecurityContextUtils securityContextUtils;

  @Value("${app.upload.dir}")
  private String uploadDir;


  @Transactional
  public MemberDto create(SignupRequest request) {
    Member newMember = Member.builder()
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .name(request.getName())
        .birth(request.getBirth())
        .build();

    Member member = repository.save(newMember);
    member.addAuthority(AuthGrade.NORMAL);

    return mapper.toDto(member);
  }

  public MemberDto findByEmail(String email) {
    Member member = repository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("member is not exists by email : " + email));
    return mapper.toDto(member);
  }

  @Transactional
  public void uploadProfilePicture(MultipartFile profile) throws FileUploadException {
    Member member = securityContextUtils.getLoginMember();
    Path uploadPath = Paths.get(
        uploadDir + File.separator
            + member.getId() + File.separator
            + StringUtils.cleanPath(FileHelper.getFileNameServer(profile)));
    // file upload
    try {
      File file = new File(uploadPath.toString());

      // *** 파일 업로드
      FileUtils.writeByteArrayToFile(file, profile.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
      throw new FileUploadException("Could not store file : " + profile.getOriginalFilename());
    }
    member.updateProfilePicture(uploadPath.toString());
  }

  public InputStream retrieveMyProfilePictureInputStream() throws FileNotFoundException {
    Member member = securityContextUtils.getLoginMember();
    return new FileInputStream(member.getProfilePicturePath());
  }

  public List<PetDto> findMyPets() {
    Member member = securityContextUtils.getLoginMember();
    List<Pet> pets = member.getPets();
    return petService.toDto(pets);
  }


}
