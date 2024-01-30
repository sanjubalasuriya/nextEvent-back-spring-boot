package com.nextevent.service.serviceImpl;


import com.nextevent.dto.requestDto.RefreshTokenRequestDto;
import com.nextevent.dto.requestDto.SignInRequestDto;
import com.nextevent.dto.responsetDto.JwtAuthenticationResponseDto;
import com.nextevent.entity.User;
import com.nextevent.repository.UserRepo;
import com.nextevent.security.JWTService;
import com.nextevent.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepo userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

//    public User signUp(SignUpRequestDto signUpRequestDto) {
//        User user = new User();
//
//        user.setEmail(signUpRequestDto.getEmail());
//        user.setFirstName(signUpRequestDto.getFirstName());
//        user.setLastName(signUpRequestDto.getLastName());
//        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
//        user.setRole(Role.USER);
//
//        return userRepository.save(user);
//    }

    public JwtAuthenticationResponseDto signIn(SignInRequestDto signInRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestDto.getEmail(), signInRequestDto.getPassword()));

        var user = userRepository.findByEmail(signInRequestDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid Email or Password"));

//        var jwt = jwtService.generateToken(new HashMap<>(),user);
//        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        var jwt = jwtService.createToken(user, user.getRole());
        var refreshToken = jwtService.createRefreshToken(user, user.getRole());

        JwtAuthenticationResponseDto jwtAuthenticationResponseDto = new JwtAuthenticationResponseDto();
        jwtAuthenticationResponseDto.setToken(jwt);
        jwtAuthenticationResponseDto.setRefreshToken(refreshToken);
        return jwtAuthenticationResponseDto;
    }

    public JwtAuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto){
        String userEmail = jwtService.extractUsername(refreshTokenRequestDto.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequestDto.getToken(),user)){
//            var jwt = jwtService.generateToken(new HashMap<>(),user);
            var jwt = jwtService.createToken(user, user.getRole());

            JwtAuthenticationResponseDto jwtAuthenticationResponseDto = new JwtAuthenticationResponseDto();

            jwtAuthenticationResponseDto.setToken(jwt);
            jwtAuthenticationResponseDto.setRefreshToken(refreshTokenRequestDto.getToken());
            return jwtAuthenticationResponseDto;
        }

        return null;
    }
}
