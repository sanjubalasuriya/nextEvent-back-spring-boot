package com.nextevent.service;


import com.nextevent.dto.requestDto.RefreshTokenRequestDto;
import com.nextevent.dto.requestDto.SignInRequestDto;
import com.nextevent.dto.responsetDto.JwtAuthenticationResponseDto;

public interface AuthenticationService {

//    User signUp(SignUpRequestDto signUpRequestDto);

    JwtAuthenticationResponseDto signIn(SignInRequestDto signInRequestDto);

    JwtAuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);
}
