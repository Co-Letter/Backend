package com.example.coletter.model.dto;

import lombok.Getter;

@Getter
public class CreateMailboxRequest {

    private String title;


    public CreateMailboxRequest(String title) {
        this.title = title;
    }
}
