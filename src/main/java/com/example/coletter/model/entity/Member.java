package com.example.coletter.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Member_Id;

    @Column(name = "Nickname")
    private String Member_NickName;
    // 리스트에서 랜덤으로 꺼내오기

    @Column(name = "ProfileImage")
    private String Member_Profile_Image;
    //default 값 협의 후 설정

    @Column(name = "KakaoId")
    private Long Member_Kakao_Id;

    @Column(name = "KakaoEmail")
    private String Member_Kakao_Email;

    @Column(name = "Role")
    @ColumnDefault("User")
    private Enum Member_Role;

    @CreationTimestamp
    @Column(name = "CreateAt")
    private LocalDateTime Member_Create_At = LocalDateTime.now();;

    @Builder
    public Member(String member_NickName, String member_Kakao_Email, Long member_Kakao_Id) {
        this.Member_NickName = member_NickName;
        this.Member_Kakao_Email = member_Kakao_Email;
        this.Member_Kakao_Id = member_Kakao_Id;
    }

}
