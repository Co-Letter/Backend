package com.example.coletter.kakao;

import com.example.coletter.oauth.OAuthInfoResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoInfoResponse implements OAuthInfoResponse {

    @JsonProperty("id")
    private Long kakao;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoAccount {
        private KakaoProfile profile;
        private String email;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoProfile {
        private String profile_image_url;
    }


    @Override
    public String getEmail() {
        return kakaoAccount.email;
    }

    @Override
    public Long getKakao() {
        return kakao;
    }

    @Override
    public String getProfile_image_url() {
        return kakaoAccount.profile.profile_image_url;
    }

}