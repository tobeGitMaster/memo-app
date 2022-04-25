package com.vcnc.memo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Memo extends BaseTimeEntity {

    @Builder
    public Memo(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memo_id")
    private Long id;

    @Column(length = 200)
    private String title;

    @Column(length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Memo createMemo(String title, String content, Member member) {
        Memo memo = Memo.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
        member.getMemos().add(memo);

        return memo;
    }

}
