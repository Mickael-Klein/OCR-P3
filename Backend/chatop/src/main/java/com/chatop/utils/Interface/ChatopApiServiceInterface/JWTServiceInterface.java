package com.chatop.utils.Interface.ChatopApiServiceInterface;

import org.springframework.security.oauth2.jwt.Jwt;

public interface JWTServiceInterface {
  String generateToken(Long id);
  Jwt decodeToken(String token);
  Long getUserIdFromJwtLong(Jwt jwt);
  Boolean areUserIdMatching(Long userIdFromJwt, Long userIdFromRequestPayload);
}
