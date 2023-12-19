package com.example.coletter.oauth;

import com.example.coletter.model.entity.Member;
import com.example.coletter.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    private final List<String> firstNames = Arrays.asList("가냘픈", "강한", "거친", "고운", "괜찮은");
    private final List<String> middleNames = Arrays.asList("붉은", "검은", "금빛", "은빛", "하얀");
    private final List<String> lastNames = Arrays.asList("강아지", "고양이", "거북이", "기린", "곰");

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long userId = findOrCreateUser(oAuthInfoResponse);
        return authTokensGenerator.generate(userId);
    }

    private Long findOrCreateUser(OAuthInfoResponse oAuthInfoResponse) {

        return memberRepository.findByKakaoId(oAuthInfoResponse.getKakao())
                .map(Member::getMemberId)
                .orElseGet(() -> newUser(oAuthInfoResponse));
    }

    private Long newUser(OAuthInfoResponse oAuthInfoResponse) {

        Member member = Member.builder()
                .member_nickname(randomnamegenerater()) ///차후 리스트에서 가져오기
                .member_profile_image(oAuthInfoResponse.getProfile_image_url())
                .member_kakao_email(oAuthInfoResponse.getEmail())
                .kakaoId(oAuthInfoResponse.getKakao())
                .build();

        return memberRepository.save(member).getMemberId();
    }

    private String randomnamegenerater(){

        Random random = new Random();
        String firstName = getRandomElement(firstNames, random);
        String middleName = getRandomElement(middleNames, random);
        String lastName = getRandomElement(lastNames, random);

        return firstName + middleName + lastName;
    }

    private String getRandomElement(List<String> list, Random random) {
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}
