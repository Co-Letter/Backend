package com.example.coletter.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.bytecode.enhance.spi.EnhancementInfo;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "letter_id")
    private long id;

    @Column(name = "letter_content")
    private String content;

    @Column(name = "letter_writer")
    private String writer;

    @Column(name = "letter_secret")
    private boolean secret;

    private Timestamp letterCreateAt;

    private int letterBackground;

    private boolean letterReport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="mailbox_id")
//    private MailBox mailBox;



}

