/*
 * Copyright (C) 2018-2022 Bigin(https://bigin.io/main) - All Rights Reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 * Written by dev team, henry<jognsang@bigin.io>
 * Last modified on 2022/9/1
 */

package io.ddd.togaether.test;

import io.ddd.togaether.dao.MemberRepository;
import io.ddd.togaether.dao.PetRepository;
import io.ddd.togaether.dto.SignupRequest;
import io.ddd.togaether.model.Member;
import io.ddd.togaether.service.MemberService;
import io.ddd.togaether.service.PetService;
import io.ddd.togaether.test.member.MemberFixture;
import io.ddd.togaether.test.pet.PetFixture;
import io.ddd.togaether.util.FileReadException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * create on 2022/08/31. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
@Component
@Profile({"local"})
@RequiredArgsConstructor
@Slf4j
public class InitData {

  private final ApplicationContext applicationContext;

  private final MemberService memberService;
  private final MemberRepository memberRepository;
  private final PetService petService;

  private final PetRepository petRepository;


  @PostConstruct
  public void init() throws Exception {
    try {
      memberRepository.findAll().forEach(m -> {
        memberRepository.delete(m);
      });

      petRepository.findAll().forEach(p -> {
        petRepository.delete(p);
      });

      // add member
      addMembers();
      addPets();
      addPetLike();
    } catch (Exception e) {
      e.printStackTrace();
      log.error("error : {}", e.getStackTrace());
      log.error("init data error!");
      log.error("error : {}", e.getMessage());
    }

  }

  private void addPetLike() {
    Member member1 = memberRepository.findByEmail(MemberFixture.member1().getEmail()).get();
    Member member2 = memberRepository.findByEmail(MemberFixture.member2().getEmail()).get();
    Member member3 = memberRepository.findByEmail(MemberFixture.member3().getEmail()).get();

    petService.addFollower(member1.getPets().get(0).getId(), member2);
    petService.addFollower(member1.getPets().get(0).getId(), member3);
    petService.addFollower(member3.getPets().get(0).getId(), member1);


  }

  private void addPets() throws FileReadException {
    Member member1 = memberRepository.findByEmail(MemberFixture.member1().getEmail()).get();
    Member member2 = memberRepository.findByEmail(MemberFixture.member2().getEmail()).get();
    Member member3 = memberRepository.findByEmail(MemberFixture.member3().getEmail()).get();
    petRepository.save(PetFixture.retriever(member1));
    petRepository.save(PetFixture.greyHound(member2));
    petRepository.save(PetFixture.samoyed(member3));

  }

  private void addMembers() {
    SignupRequest member1 = MemberFixture.member1();
    SignupRequest member2 = MemberFixture.member2();
    SignupRequest member3 = MemberFixture.member3();

    memberService.create(member1);
    memberService.create(member2);
    memberService.create(member3);
  }


}
