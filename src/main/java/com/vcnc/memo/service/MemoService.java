package com.vcnc.memo.service;

import com.vcnc.memo.domain.Member;
import com.vcnc.memo.domain.Memo;
import com.vcnc.memo.dto.reponse.MemoResponseDto;
import com.vcnc.memo.dto.request.MemoSaveRequestDto;
import com.vcnc.memo.dto.request.MemoUpdateRequestDto;
import com.vcnc.memo.repository.MemberRepository;
import com.vcnc.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MemoResponseDto save(MemoSaveRequestDto memoSaveRequestDto) {
        Member member = memberRepository.findById(memoSaveRequestDto.getMemberId()).orElseThrow(() -> new NoSuchElementException("존재하지 않는 멤버id입니다."));

        return new MemoResponseDto(memoRepository.save(Memo.builder()
                .title(memoSaveRequestDto.getTitle())
                .content(memoSaveRequestDto.getContent())
                .member(member)
                .build()));
    }

    @Transactional
    public MemoResponseDto update(Long id, MemoUpdateRequestDto memoUpdateRequestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 메모id 입니다."));
        memo.update(memoUpdateRequestDto.getTitle(), memoUpdateRequestDto.getContent());

        return new MemoResponseDto(memo);
    }

    @Transactional
    public void delete(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 메모id 입니다."));
        memoRepository.delete(memo);
    }

    public List<MemoResponseDto> findAll() {
        return memoRepository.findAllWithMember().stream().map(MemoResponseDto::new).collect(Collectors.toList());
    }

    public MemoResponseDto findOne(Long id) {
        Memo memo = memoRepository.findOneWithMember(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 메모id 입니다."));
        return new MemoResponseDto(memo);
    }

}
