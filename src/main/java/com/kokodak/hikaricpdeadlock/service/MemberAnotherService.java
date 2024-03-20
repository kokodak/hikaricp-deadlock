package com.kokodak.hikaricpdeadlock.service;

import com.kokodak.hikaricpdeadlock.domain.Member;
import com.kokodak.hikaricpdeadlock.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberAnotherService {

    private final MemberRepository memberRepository;

    public MemberAnotherService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void anotherSave() {
        System.out.println("MemberAnotherService.anotherSave() 에서 Connection 1개 추가로 사용 예정...");

        final Member member = new Member("kukuduk");
        memberRepository.save(member);
    }
}
