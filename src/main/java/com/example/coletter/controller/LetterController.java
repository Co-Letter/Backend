package com.example.coletter.controller;

import com.example.coletter.common.BaseException;
import com.example.coletter.common.BaseResponse;
import com.example.coletter.common.BaseResponseStatus;
import com.example.coletter.model.dto.CreateLetterRequest;
import com.example.coletter.model.dto.CreateLetterResponse;
import com.example.coletter.model.dto.LetterResponse;
import com.example.coletter.service.LetterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/letter")
@CrossOrigin(origins = "*")
public class LetterController {

    private final LetterService letterService;


    @GetMapping("/{mailboxId}")
    public BaseResponse<LetterResponse> createLetter(@RequestHeader("Authorization") String accessToken, @PathVariable Long mailboxId) {
        LetterResponse letterResponse = letterService.selectLetter(accessToken,mailboxId);
        return new BaseResponse<>(letterResponse);
    }


    @PostMapping("/{mailboxId}")
    public BaseResponse<CreateLetterResponse> createLetter(@RequestHeader("Authorization") String accessToken, @PathVariable Long mailboxId, @RequestBody @Valid CreateLetterRequest request) {
        CreateLetterResponse createLetterResponse = letterService.createLetter(accessToken,mailboxId,request);
        return new BaseResponse<>(createLetterResponse);
    }



    @DeleteMapping("/{mailboxId}")
    public BaseResponse<String> deleteLetter(@RequestHeader("Authorization") String accessToken,@PathVariable Long mailboxId) {

        letterService.deleteLetter(accessToken,mailboxId);

        return new BaseResponse<>("SUCCESS");
    }


}
