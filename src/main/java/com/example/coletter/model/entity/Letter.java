package com.example.coletter.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.bytecode.enhance.spi.EnhancementInfo;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "letter_id")
    private Long id;

    @Column(name = "letter_content")
    private String content;

    @Column(name = "letter_writer", nullable = false)
    private String writer;

    @Column(name = "letter_secret", nullable = false)
    private boolean secret;

    @Column(name = "leter_create_at", nullable = false)
    @CreatedDate
    private LocalDateTime createAt;

    @Column(name = "letter_background", nullable = false)
    private int background;

    @Column(name = "letter_report", nullable = false)
    private Boolean report;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mailbox_id", nullable = false)
    private Mailbox mailbox;

    @Builder
    public Letter(String content,String writer, boolean secret,int background,Member member,Mailbox mailbox) {
        this.content = content;
        this.writer =writer;
        this.secret =secret;
        this.background = background;
        this.report = false;
        this.member = member;
        this.mailbox = mailbox;
    }

}

