package com.kokodak.hikaricpdeadlock.service;

import com.kokodak.hikaricpdeadlock.domain.Member;
import com.kokodak.hikaricpdeadlock.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberAnotherService memberAnotherService;
    private final MemberRepository memberRepository;

    public MemberService(final MemberAnotherService memberAnotherService,
                         final MemberRepository memberRepository) {
        this.memberAnotherService = memberAnotherService;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long save() {
        System.out.println("MemberService.save() 에서 Connection 1개 사용 예정...");

        final Member member = new Member("kokodak");
        final Member save = memberRepository.save(member);
        memberAnotherService.anotherSave();
        return save.getId();
    }
}
