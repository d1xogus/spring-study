package com.example.maple.Service;

import com.example.maple.DTO.SignInResultDTO;
import com.example.maple.DTO.SignUpResultDTO;

public interface SignService {
    SignUpResultDTO signUp(String id, String pw, String name, String role);
    SignInResultDTO signIn(String id, String pw) throws RuntimeException;
}
