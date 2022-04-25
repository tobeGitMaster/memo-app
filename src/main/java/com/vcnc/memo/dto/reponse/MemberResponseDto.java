package com.vcnc.memo.dto.reponse;

import com.vcnc.memo.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private Long id;
    private String nickname;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
    }

}
