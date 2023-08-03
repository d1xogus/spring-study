package com.example.maple.security;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


public enum CommonResponse {

    SUCCESS(0, "Success"), FAIL(-1, "Fail");

    int code;
    String msg;

    CommonResponse(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
