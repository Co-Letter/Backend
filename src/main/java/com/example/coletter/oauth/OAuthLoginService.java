package com.example.coletter.oauth;

import com.example.coletter.model.entity.Member;
import com.example.coletter.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long userId = findOrCreateUser(oAuthInfoResponse);
        return authTokensGenerator.generate(userId);
    }

    private Long findOrCreateUser(OAuthInfoResponse oAuthInfoResponse) {

        return memberRepository.findByKakaoId(oAuthInfoResponse.getKakao())
                .map(Member::getMember_id)
                .orElseGet(() -> newUser(oAuthInfoResponse));
    }

    private Long newUser(OAuthInfoResponse oAuthInfoResponse) {

        Member member = Member.builder()
                ///.member_NickName() ///차후 리스트에서 가져오기
                .member_profile_image(oAuthInfoResponse.getProfile_image_url())
                .member_kakao_email(oAuthInfoResponse.getEmail())
                .kakaoId(oAuthInfoResponse.getKakao())
                .build();

        return memberRepository.save(member).getMember_id();
    }
}
