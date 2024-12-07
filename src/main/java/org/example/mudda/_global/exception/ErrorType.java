package org.example.mudda._global.exception;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public enum ErrorType {

    CAPSULE_GOALTIME_NOT_ALLOWED(400, "캡슐 열람 시간은 1시간 이상이어야 합니다."),
    ;

    private boolean success;
    private int code;
    private String msg;

    ErrorType(int code, String msg) {
        this.success = false;
        this.code = code;
        this.msg = msg;
    }

}
