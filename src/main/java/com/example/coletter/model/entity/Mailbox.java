package com.example.coletter.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Mailbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mailbox_id")
    private Long mailboxId;

    @Column(name = "mailbox_title")
    private String title;


    @Column(name = "mailbox_create_at",updatable = false)
    @CreatedDate
    private LocalDateTime create_at;

    @JsonIgnore
    @OneToOne(mappedBy = "mailbox",fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "mailbox", cascade = CascadeType.ALL)
    private List<Letter> letters = new ArrayList<>();

    public Mailbox(String title) {
        this.title = title;
    }
    public Mailbox(Long mailboxId) {
        this.mailboxId = mailboxId;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

}
