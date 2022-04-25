package com.vcnc.memo;

import com.vcnc.memo.domain.Member;
import com.vcnc.memo.domain.Memo;
import com.vcnc.memo.repository.MemberRepository;
import com.vcnc.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@EnableJpaAuditing
@SpringBootApplication
public class MemoApplication implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final MemoRepository memoRepository;

    public static void main(String[] args) {
        SpringApplication.run(MemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Member member = Member.builder().nickname("홍길동").build();
        memberRepository.save(member);
        
        List<Memo> memos = Arrays.asList(
                Memo.builder().member(member).title("제목1").content("내용1").build(),
                Memo.builder().member(member).title("제목2").content("내용2").build(),
                Memo.builder().member(member).title("제목3").content("내용3").build(),
                Memo.builder().member(member).title("제목4").content("내용4").build()
        );
        memoRepository.saveAll(memos);

        Member member2 = Member.builder().nickname("김철수").build();
        memberRepository.save(member2);
        
        List<Memo> memos2 = Arrays.asList(
                Memo.builder().member(member2).title("제목10").content("내용10").build(),
                Memo.builder().member(member2).title("제목20").content("내용20").build(),
                Memo.builder().member(member2).title("제목30").content("내용30").build(),
                Memo.builder().member(member2).title("제목40").content("내용40").build()
        );
        memoRepository.saveAll(memos2);
    }
}
