package com.example.coletter.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    @Column(name = "Nickname")
    private String member_nickname;
    // 리스트에서 랜덤으로 꺼내오기

    @Column(name = "ProfileImage")
    private String member_profile_image;
    //카카오프로필이미지

    @Column(name = "KakaoId")
    private Long kakaoId;

    @Column(name = "KakaoEmail")
    private String member_kakao_email;

    @Column(name = "Role")
    @ColumnDefault("User")
    private Enum member_role;

    @CreatedDate
    @Column(name = "CreateAt")
    private LocalDateTime member_create_at = LocalDateTime.now();;

    @Builder
    public Member(String member_profile_image ,String member_nickname, String member_kakao_email, Long kakaoId) {
        this.member_profile_image = member_profile_image;
        this.member_nickname = member_nickname;
        this.member_kakao_email = member_kakao_email;
        this.kakaoId = kakaoId;
    }

}
