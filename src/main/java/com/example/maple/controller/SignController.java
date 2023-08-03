package com.example.maple.controller;

import com.example.maple.DTO.SignInResultDTO;
import com.example.maple.DTO.SignUpResultDTO;
import com.example.maple.Service.SignService;
import com.example.maple.Service.SignServiceImpl;
import com.example.maple.security.JwtTokenProvider;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SignController {
    private final SignService signService;

    private final Logger LOGGER = LoggerFactory.getLogger(SignController.class);
    @PostMapping("/sign-in")
    public SignInResultDTO signIn(@ApiParam(value = "ID", required = true) @RequestParam String id,
                                  @ApiParam(value = "Password",required = true) @RequestParam String password) throws RuntimeException{
        LOGGER.info("[signIn]로그인을 시도하고 있습니다. id : {}, pw : ****", id);
        SignInResultDTO signInResultDTO = signService.signIn(id,password);
        if (signInResultDTO.getCode() == 0){
            LOGGER.info("[signIn] 정상적으로 로그인이 되었습니다. id : {}, token : {}", id, signInResultDTO.getToken());
        }
        return signInResultDTO;
    }

    @PostMapping("/sign-up")
    public SignUpResultDTO signIn(@ApiParam(value = "ID", required = true) @RequestParam String id,
                                  @ApiParam(value = "Password",required = true) @RequestParam String password,
                                  @ApiParam(value = "name", required = true) @RequestParam String name,
                                  @ApiParam(value = "role",required = true) @RequestParam String role) throws RuntimeException {
        SignUpResultDTO signUpResultDTO = signService.signUp(id,password,name, role);
        return signUpResultDTO;
    }

    @GetMapping("/exception")
    public void exceptionTest() throws RuntimeException{
        throw new RuntimeException("접근제한");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String,String>> ExceptionHandler(RuntimeException e){
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러");
        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}
