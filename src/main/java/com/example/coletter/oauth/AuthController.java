package com.example.coletter.oauth;

import com.example.coletter.kakao.KakaoLoginParams;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final OAuthLoginService oAuthLoginService;
    private final OAuthLogoutService oAuthLogoutService;

    @Operation(summary = "카카오 로그인 및 회원가입")
    @PostMapping("/kakaoLogin")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        System.out.println(params);
        log.info("{}",params);
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/kakaoLogout")
    public String logout(@RequestHeader("Authorization") String authorization) {
        return oAuthLogoutService.logout(authorization);
    }



}
