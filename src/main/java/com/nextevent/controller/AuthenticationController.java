package com.nextevent.controller;


import com.nextevent.dto.requestDto.RefreshTokenRequestDto;
import com.nextevent.dto.requestDto.SignInRequestDto;
import com.nextevent.dto.responsetDto.JwtAuthenticationResponseDto;
import com.nextevent.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;

//    @PostMapping("/signup")
//    public ResponseEntity<User> signup(@RequestBody SignUpRequestDto signUpRequestDto){
//        return ResponseEntity.ok(authenticationService.signUp(signUpRequestDto));
//    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto){
        return ResponseEntity.ok(authenticationService.signIn(signInRequestDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponseDto> refresh(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequestDto));
    }
}
