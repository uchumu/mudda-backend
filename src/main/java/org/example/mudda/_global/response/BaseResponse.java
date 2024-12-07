package org.example.mudda._global.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> {
    private boolean success;
    private Integer code;
    private String message;
    private T data;


    @Builder
    public BaseResponse(boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> of(MsgType msgType, T data) {
        return BaseResponse.<T>builder()
                .success(msgType.isSuccess())
                .code(msgType.getCode())
                .message(msgType.getMsg())
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> of(boolean isSuccess, Integer code, String message, T data) {
        return BaseResponse.<T>builder()
                .success(isSuccess)
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> validError(String msg, T data) {
        return BaseResponse.<T>builder()
                .success(false)
                .code(500)
                .message(msg)
                .data(data)
                .build();
    }
}
