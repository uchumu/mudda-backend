package org.example.mudda._global.response;

import lombok.Getter;

@Getter
public enum MsgType {

    SEARCH_SUCCESSFULLY(true, 0, "조회 성공"),
    INSERT_SUCCESSFULLY(true, 0, "데이터 생성 성공"),
    DELETE_SUCCESSFULLY(true, 0, "데이터 삭제 성공"),

    CAPSULE_INFO_EMPTY(false, 400, "캡슐이 존재하지 않습니다."),
    CATEGORY_EMPTY(false, 401, "카테고리를 입력해주세요"),
    BOOK_INFO_NULL(false, 402, "교재 정보가 없습니다"),

    GLOBAL_EXCEPTION_ERROR(false, 500, ""),
    CAPSULE_GOAL_TIME_NOT_ALLOWED(false ,501, "캡슐 열람 시간은 1시간 이상이어야 합니다."),
    CAPSULE_CODE_ERROR(false ,502, "캡슐 코드를 확인해주세요."),
    IMAGE_FILE_EMPTY(false, 503, "이미지 파일이 없습니다."),
    IMAGE_FILE_TYPE_ERROR(false, 504, "이미지 파일만 업로드 가능합니다."),
    CAPSULE_MESSAGE_LIMIT(false, 505, "메시지는 255개까지만 입력 가능합니다.")

    ;

    private boolean success;
    private int code;
    private String msg;

    MsgType(boolean success, int code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }
}
