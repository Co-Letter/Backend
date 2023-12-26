package com.example.coletter.controller;

import com.example.coletter.common.BaseResponse;
import com.example.coletter.model.dto.UpdateMailboxRequest;
import com.example.coletter.model.entity.Letter;
import com.example.coletter.service.MailboxService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mailbox")
public class MailboxController {

    private final MailboxService mailboxService;

    @Operation(summary = "메일 갯수")
    @GetMapping("/countmail")
    public BaseResponse<Long> countMail(@RequestHeader("Authorization")String accessToken){
        Long count = mailboxService.countMail(accessToken);
        return new BaseResponse<>(count);
    }


    @Operation(summary = "메일 제목변경")
    @PatchMapping("")
    public BaseResponse<Long> putMailbox(@RequestHeader("Authorization")String accessToken,@RequestBody @Valid UpdateMailboxRequest updateMailboxRequest) {

        Long id = mailboxService.updateMailboxTitle(accessToken,updateMailboxRequest.getTitle());
        return new BaseResponse<>(id);
    }

    @Operation(summary = "메일함 전체 조회")
    @GetMapping("/getallmail")
    public BaseResponse<List> getAllLetter(@RequestHeader("Authorization") String accessToken){
        List<Letter> letters = mailboxService.getAllLetter(accessToken);
        return new BaseResponse<>(letters);
    }



}
