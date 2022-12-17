package io.ddd.togaether.service;

import io.ddd.togaether.dao.MemberRepository;
import io.ddd.togaether.dto.MemberDto;
import io.ddd.togaether.dto.SignupRequest;
import io.ddd.togaether.dto.mapper.MemberMapper;
import io.ddd.togaether.model.AuthGrade;
import io.ddd.togaether.model.Member;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@RequiredArgsConstructor
@Slf4j
public class MemberService {
  private final MemberRepository repository;
  private final MemberMapper mapper;

  private final PasswordEncoder passwordEncoder;


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
}
