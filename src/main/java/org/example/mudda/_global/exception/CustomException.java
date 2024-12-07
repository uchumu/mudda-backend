package org.example.mudda._global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.mudda._global.response.MsgType;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException{
    private final MsgType msgType;

    @Override
    public String getMessage() {
        return msgType.getMsg();
    }
}
