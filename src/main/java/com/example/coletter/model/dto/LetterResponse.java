package com.example.coletter.model.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LetterResponse {

    private String content;
    private String writer;
    private boolean secret;
    private int background;
}
