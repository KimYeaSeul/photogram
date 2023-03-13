package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException{
    private Map<String, String> errorMap;

    public CustomValidationApiException(String message, Map<String, String> errorMap){
        super(message); // 부모한테 던지면 getMessage 사용 가능
        // 내가 들고있는게 아니라 부모한테 던질거라 여기서 선언할 필요 없음. 걍 넘기면 됨.
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap(){
        return errorMap;
    }
}
