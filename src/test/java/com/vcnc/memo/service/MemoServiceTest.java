package com.vcnc.memo.service;

import com.vcnc.memo.domain.Member;
import com.vcnc.memo.domain.Memo;
import com.vcnc.memo.dto.reponse.MemoResponseDto;
import com.vcnc.memo.dto.request.MemoSaveRequestDto;
import com.vcnc.memo.dto.request.MemoUpdateRequestDto;
import com.vcnc.memo.repository.MemberRepository;
import com.vcnc.memo.repository.MemoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemoServiceTest {

    @Autowired
    MemoService memoService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemoRepository memoRepository;

    @Test
    void 메모단건등록_테스트() {
        // given
        Member member = Member.builder().nickname("홍길동").build();
        memberRepository.save(member);

        MemoSaveRequestDto memoSaveRequestDto = MemoSaveRequestDto.builder().memberId(member.getId()).title("제목").content("내용").build();

        // when
        MemoResponseDto memoResponseDto = memoService.save(memoSaveRequestDto);

        // then
        assertThat(memoResponseDto.getTitle()).isEqualTo(memoSaveRequestDto.getTitle());
        assertThat(memoResponseDto.getContent()).isEqualTo(memoSaveRequestDto.getContent());
        assertThat(memoResponseDto.getMemberId()).isEqualTo(memoSaveRequestDto.getMemberId());
    }

    @Test
    void 메모다건등록_조회_테스트() {
        // given
        Member member = Member.builder().nickname("홍길동").build();
        memberRepository.save(member);

        Memo memo = Memo.builder().member(member).title("제목").content("내용").build();
        memoRepository.save(memo);

        Memo memo2 = Memo.builder().member(member).title("제목2").content("내용2").build();
        memoRepository.save(memo2);

        // when
        List<MemoResponseDto> memos = memoService.findAll();

        // then
        assertThat(memos.size()).isEqualTo(2);
    }

    @Test
    void 메모수정_테스트() {
        // given
        Member member = Member.builder().nickname("홍길동").build();
        memberRepository.save(member);

        Memo memo = Memo.builder().member(member).title("제목").content("내용").build();
        memoRepository.save(memo);

        MemoUpdateRequestDto memoUpdateRequestDto = MemoUpdateRequestDto.builder().title("제목2").content("내용2").build();

        // when
        memoService.update(memo.getId(), memoUpdateRequestDto);

        // then
        MemoResponseDto responseDto = memoService.findOne(memo.getId());
        assertThat(responseDto.getTitle()).isEqualTo(memoUpdateRequestDto.getTitle());
        assertThat(responseDto.getContent()).isEqualTo(memoUpdateRequestDto.getContent());
    }

    @Test
    void 메모삭제_테스트() {
        // given
        Member member = Member.builder().nickname("홍길동").build();
        memberRepository.save(member);

        Memo memo = Memo.builder().member(member).title("제목").content("내용").build();
        memoRepository.save(memo);

        // when
        memoService.delete(memo.getId());

        // then
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            memoService.findOne(memo.getId());
        });
    }
}