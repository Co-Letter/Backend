package com.example.coletter.controller;

import com.example.coletter.jwt.JwtTokenProvider;
import com.example.coletter.model.entity.Mailbox;
import com.example.coletter.model.entity.Member;
import com.example.coletter.service.MailboxService;
import com.example.coletter.service.MemberService;
import com.example.coletter.common.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberController(MemberService memberService, JwtTokenProvider jwtTokenProvider){
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Operation(summary = "내 정보 조회")
    @GetMapping("/profile")
    public BaseResponse<Member> getMemberProfile(@RequestHeader("Authorization") String accessToken) {
       try{
           Long Id = jwtTokenProvider.extractId(accessToken);
           Member member = memberService.getMember(Id);
           return new BaseResponse<>(member);
       } catch (BaseException exception) {
           return new BaseResponse<>(exception.getStatus());
       }

    }

    @Operation(summary = "회원탈퇴")
    @DeleteMapping("/delete")
    public BaseResponse<String> deleteUserProfile(@RequestHeader("Authorization") String accessToken) {
        try{
            Long Id = jwtTokenProvider.extractId(accessToken);
            memberService.deleteUser(Id);
            log.info("삭제실행");
            String result = "삭제되었습니다.";

            return new BaseResponse<>(result);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
