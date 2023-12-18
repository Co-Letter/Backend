package com.example.coletter.model.dto;

import com.example.coletter.model.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class CreateLetterRequest {

    private String content;

    private String writer;

    private Boolean secret;

    private int background;



}
