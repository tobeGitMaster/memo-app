package com.vcnc.memo.controller;

import com.vcnc.memo.domain.Member;
import com.vcnc.memo.dto.reponse.MemberResponseDto;
import com.vcnc.memo.dto.request.MemberSaveRequestDto;
import com.vcnc.memo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/members")
    public MemberResponseDto save(@Valid @RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        return memberService.save(memberSaveRequestDto);
    }

    @GetMapping("/api/members")
    public List<MemberResponseDto> findAll() {
        return memberService.findAll();
    }

}
