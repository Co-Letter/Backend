package com.example.coletter.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mailbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mailbox_id")
    private Long id;

    @Column(name = "mailbox_title")
    private String title;


    @Column(name = "mailbox_create_at")
    @CreatedDate
    private LocalDateTime create_at;

    @OneToOne(mappedBy = "mailbox")
    private Member member;

    @OneToMany(mappedBy = "mailbox")
    private List<Letter> letters = new ArrayList<>();

}
