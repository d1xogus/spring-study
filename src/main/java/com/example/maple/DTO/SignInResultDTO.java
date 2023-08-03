package com.example.maple.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SignInResultDTO extends SignUpResultDTO{
    private String token;
}
