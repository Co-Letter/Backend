package com.example.coletter.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

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

    @Column(name = "ProfileImage")
    private String Member_Profile_Image;

    @Column(name = "KakaoId")
    private String Member_Kakao_Id;

    @Column(name = "KakaoEmail")
    private String Member_Kakao_Email;

    @Column(name = "Role")
    @ColumnDefault("User")
    private Enum Member_Role;

    @Column(name = "CreateAt")
    private Date Member_Create_At;
}
