package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// 다 낚아 챈다고?
@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
    //js return
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e){

        // 1. 클라이언트에게 응답할 때는 Script가 좋음. -> 브라우저가 응답 받음
        // 2. Ajax 통신 - CMRespDto 사용 -> 개발자가 응답 받음
        // 3. Android 통신 - CMRespDto 사용 -> 개발자가 응답 받음
//        return new CMRespDto(-1, e.getMessage(), e.getErrorMap());
        if(e.getErrorMap() == null) return Script.back(e.getMessage());
        else return Script.back(e.getErrorMap().toString());
    }

//    data return
    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationApiException(CustomValidationApiException e){
        return new ResponseEntity<>( new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e){
        return new ResponseEntity<>( new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
}
