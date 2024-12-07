package org.example.mudda._global.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.mudda._global.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class )
    public ResponseEntity<BaseResponse> methodValidException(MethodArgumentNotValidException e) {
        log.error("methodValidException throw Exception : {}", e.getBindingResult());

        return new ResponseEntity<>(BaseResponse.of(false, 500, e.getBindingResult().hasErrors()?e.getBindingResult().getAllErrors().get(0).getDefaultMessage():"", null), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = CustomException.class)
    protected ResponseEntity<BaseResponse> handleCustomException(CustomException e) {
        log.error("handleDataException throw Exception : {}", e.getMsgType());

        return new ResponseEntity<>(BaseResponse.of(false, e.getMsgType().getCode(), e.getMsgType().getMsg(), null), HttpStatus.BAD_REQUEST);

    }

}
