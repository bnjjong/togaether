/*
 * Created By dogfootmaster@gmail.com on 2023
 * This program is free software
 *
 * @author <a href=“mailto:dogfootmaster@gmail.com“>Jongsang Han</a>
 * @since 2023/01/14
 */

package io.ddd.togaether.service;

import io.ddd.togaether.dto.MemberDto;
import io.ddd.togaether.dto.PetDto;
import io.ddd.togaether.dto.SignupRequest;
import io.ddd.togaether.model.Member;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

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
public interface MemberService {

  MemberDto create(SignupRequest request);
  MemberDto findByEmail(String email);
  void uploadProfilePicture(Member member, MultipartFile profile) throws FileUploadException;
  InputStream retrieveMyProfilePictureInputStream(Member member) throws FileNotFoundException;
  List<PetDto> findMyPets(Member loginMember);

}
