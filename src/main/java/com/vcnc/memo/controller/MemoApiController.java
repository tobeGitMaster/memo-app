package com.vcnc.memo.controller;

import com.vcnc.memo.dto.reponse.MemoResponseDto;
import com.vcnc.memo.dto.request.MemoSaveRequestDto;
import com.vcnc.memo.dto.request.MemoUpdateRequestDto;
import com.vcnc.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemoApiController {

    private final MemoService memoService;

    @PostMapping("/api/memos")
    public MemoResponseDto save(@Valid @RequestBody MemoSaveRequestDto memoSaveRequestDto) {
        return memoService.save(memoSaveRequestDto);
    }

    @PutMapping("/api/memos/{memoId}")
    public MemoResponseDto update(@PathVariable("memoId") Long id, @Valid @RequestBody MemoUpdateRequestDto memoUpdateRequestDto) {
        return memoService.update(id, memoUpdateRequestDto);
    }

    @DeleteMapping("/api/memos/{memoId}")
    public void delete(@PathVariable("memoId") Long id) {
        memoService.delete(id);
    }

    @GetMapping("/api/memos")
    public List<MemoResponseDto> findAll() {
        return memoService.findAll();
    }

    @GetMapping("/api/memos/{memoId}")
    public MemoResponseDto findOne(@PathVariable("memoId") Long id) {
        return memoService.findOne(id);
    }

}
