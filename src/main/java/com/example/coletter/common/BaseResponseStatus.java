package com.example.coletter.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;


/**
  * 에러 코드 관리
 **/

@Getter
public enum BaseResponseStatus {

  /**
   * 200 : 요청 성공
   **/
  SUCCESS(true, HttpStatus.OK.value(), "요청에 성공하였습니다."),


  /**
   * 2000 : Request 오류
   */
  // Common
  INVALID_JWT(false, 2000, "유효하지 않은 JWT입니다."),
  EMPTY_JWT(false,2001,"JWT를 입력하세요"),


  // users
  MEMBER_NOT_FOUND(false,HttpStatus.NOT_FOUND.value(), "유저 정보를 찾지 못했습니다."),

  // mailbox
  MAILBOX_NOT_FOUND(false,HttpStatus.NOT_FOUND.value(), "메일함 정보를 찾지 못했습니다."),


  // letter
  LETTER_NOT_FOUND(false,HttpStatus.NOT_FOUND.value(), "편지 정보를 찾지 못했습니다."),
  LETTER_ALREADY_USED(false,HttpStatus.BAD_REQUEST.value(), "이미 작성한 편지가 있습니다.");



  /**
   * 500 : Database, Server 오류
   **/




  private final boolean success;
  private final int code;
  private final String message;

  private BaseResponseStatus(boolean success, int code, String message) {
    this.success = success;
    this.code = code;
    this.message = message;
  }

}
