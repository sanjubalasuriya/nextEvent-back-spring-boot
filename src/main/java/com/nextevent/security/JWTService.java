package com.nextevent.security;


import com.nextevent.entity.Role;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {

    String extractUsername(String token);

    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    Boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);

    String createToken(UserDetails username, Role role);

    String createRefreshToken(UserDetails username, Role role);
}
