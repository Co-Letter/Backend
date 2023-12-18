package com.example.coletter.controller;

import com.example.coletter.common.BaseResponse;
import com.example.coletter.model.dto.CreateLetterRequest;
import com.example.coletter.model.dto.CreateLetterResponse;
import com.example.coletter.service.LetterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/letter")
public class LetterController {

    private final LetterService letterService;


    @PostMapping("/{memberId}/{mailboxId}")
    public BaseResponse<CreateLetterResponse> createLetter(@PathVariable Long memberId, @PathVariable Long mailboxId, @RequestBody @Valid CreateLetterRequest request) {
        CreateLetterResponse createLetterResponse = letterService.createLetter(memberId,mailboxId,request);
        return new BaseResponse<>(createLetterResponse);
    }



    @DeleteMapping("/{memberId}/{mailboxId}")
    public BaseResponse<String> deleteLetter(@PathVariable Long memberId,@PathVariable Long mailboxId) {

        letterService.deleteLetter(memberId,mailboxId);

        return new BaseResponse<>("SUCCESS");
    }


}
