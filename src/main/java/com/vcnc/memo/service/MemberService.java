package com.vcnc.memo.service;

import com.vcnc.memo.domain.Member;
import com.vcnc.memo.dto.reponse.MemberResponseDto;
import com.vcnc.memo.dto.request.MemberSaveRequestDto;
import com.vcnc.memo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto save(MemberSaveRequestDto memberSaveRequestDto) {
        if (!memberRepository.findByNickname(memberSaveRequestDto.getNickname()).isEmpty()) {
            throw new IllegalStateException("이미 존재하는 nickname입니다.");
        }

        return new MemberResponseDto(memberRepository.save(Member.builder()
                .nickname(memberSaveRequestDto.getNickname())
                .build()));
    }

    public List<MemberResponseDto> findAll() {
        return memberRepository.findAll().stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

}
