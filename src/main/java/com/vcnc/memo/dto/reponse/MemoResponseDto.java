package com.vcnc.memo.dto.reponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vcnc.memo.domain.Memo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoResponseDto {

    private Long id;
    private String title;
    private String content;
    private String nickname;
    private Long memberId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedDate;

    public MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.content = memo.getContent();
        this.nickname = memo.getMember().getNickname();
        this.memberId = memo.getMember().getId();
        this.createdDate = memo.getCreatedDate();
        this.modifiedDate = memo.getModifiedDate();
    }

}
