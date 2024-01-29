package com.example.coletter.controller;

import com.example.coletter.common.BaseResponse;
import com.example.coletter.model.dto.CreateLetterRequest;
import com.example.coletter.model.dto.CreateLetterResponse;
import com.example.coletter.model.dto.LetterResponse;
import com.example.coletter.service.LetterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/letter")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080", "http://3.36.88.85:8080"})
public class LetterController {

    private final LetterService letterService;


    @GetMapping("/{mailboxId}")
    public BaseResponse<LetterResponse> getLetter(@RequestHeader("Authorization") String accessToken, @PathVariable Long mailboxId) {
        LetterResponse letterResponse = letterService.selectLetter(accessToken,mailboxId);
        log.info("편지조회");
        return new BaseResponse<>(letterResponse);
    }


    @PostMapping("/{mailboxId}")
    public BaseResponse<CreateLetterResponse> getLetter(@RequestHeader("Authorization") String accessToken, @PathVariable Long mailboxId, @RequestBody @Valid CreateLetterRequest request) {
        CreateLetterResponse createLetterResponse = letterService.createLetter(accessToken,mailboxId,request);
        log.info("편지생성");
        return new BaseResponse<>(createLetterResponse);
    }



    @DeleteMapping("/{mailboxId}")
    public BaseResponse<String> deleteLetter(@RequestHeader("Authorization") String accessToken,@PathVariable Long mailboxId) {

        letterService.deleteLetter(accessToken,mailboxId);
        log.info("편지삭제");
        return new BaseResponse<>("SUCCESS");
    }


}
