package com.example.coletter.controller;

import com.example.coletter.common.BaseResponse;
import com.example.coletter.service.MailboxService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mailbox")
public class MailboxController {

    private final MailboxService mailboxService;

    @Operation(summary = "메일 갯수")
    @GetMapping("/countmail")
    public BaseResponse<Long> countMail(@RequestHeader("Authorization")String accessToken){
        Long count = mailboxService.countMail(accessToken);
        return new BaseResponse<>(count);
    }



}
