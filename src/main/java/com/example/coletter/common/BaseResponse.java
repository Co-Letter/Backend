package com.example.coletter.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

import static com.example.coletter.common.BaseResponseStatus.SUCCESS;

@Getter
@JsonPropertyOrder({"success", "code", "message", "result"})
public class BaseResponse<T> {

    private final Boolean success;
    private final int code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;



    // 요청에 성공한 경우
    public BaseResponse(T result) {
        this.success = SUCCESS.isSuccess();
        this.message = SUCCESS.getMessage();
        this.code = SUCCESS.getCode();
        this.result = result;
    }

    // 요청에 실패한 경우
    public BaseResponse(BaseResponseStatus status) {
        this.success = status.isSuccess();
        this.code = status.getCode();
        this.message = status.getMessage();
    }


}
