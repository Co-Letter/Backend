package com.example.coletter.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_nickname")
    private String memberNickName;
    // 리스트에서 랜덤으로 꺼내오기

    @Column(name = "member_profile_image")
    private String memberProfileImage;
    //카카오프로필이미지

    @Column(name = "kakao_id")
    private Long kakaoId;

    @Column(name = "kakao_email")
    private String memberKakaoEmail;

    @CreatedDate
    @Column(name = "member_create_at")
    private LocalDateTime memberCreateAt = LocalDateTime.now();;

    @OneToMany(mappedBy = "member")
    private List<Letter> letters = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="mailbox_id")
    private Mailbox mailbox;

    @Builder
    public Member(String member_profile_image ,String member_nickname, String member_kakao_email, Long kakaoId) {
        this.memberProfileImage = member_profile_image;
        this.memberNickName = member_nickname;
        this.memberKakaoEmail = member_kakao_email;
        this.kakaoId = kakaoId;
    }

}
