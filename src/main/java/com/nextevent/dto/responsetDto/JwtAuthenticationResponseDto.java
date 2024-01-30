package com.nextevent.dto.responsetDto;

import lombok.Data;

@Data
public class JwtAuthenticationResponseDto {

    private String token;

    private String refreshToken;
}
