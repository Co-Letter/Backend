package com.example.coletter.model.dto;


import com.example.coletter.model.entity.Letter;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LetterResponse {

    private String content;
    private String writer;
    private boolean secret;
    private boolean report;
    private int background;


    public static LetterResponse of(Letter letter) {
        return LetterResponse.builder()
                .content(letter.getContent())
                .writer(letter.getWriter())
                .secret(letter.isSecret())
                .report(letter.getReport())
                .background(letter.getBackground())
                .build();
    }

}
