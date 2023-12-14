package com.chatop.utils.Interface.ChatopApiServiceInterface;

import org.springframework.security.oauth2.jwt.Jwt;

/**
 * Interface for JWT (JSON Web Token) service operations.
 */
public interface JWTServiceInterface {
  /**
   * Generates a JWT token for the given user ID.
   *
   * @param id The user ID.
   * @return The generated JWT token.
   */
  String generateToken(Long id);

  /**
   * Decodes the provided JWT token.
   *
   * @param token The JWT token to decode.
   * @return The decoded JWT.
   */
  Jwt decodeToken(String token);

  /**
   * Extracts the user ID from the provided JWT.
   *
   * @param jwt The decoded JWT.
   * @return The user ID extracted from the JWT.
   */
  Long getUserIdFromJwtLong(Jwt jwt);

  /**
   * Checks if the user IDs from the JWT and the request payload match.
   *
   * @param userIdFromJwt           The user ID extracted from the JWT.
   * @param userIdFromRequestPayload The user ID from the request payload.
   * @return True if the user IDs match, otherwise false.
   */
  Boolean areUserIdMatching(Long userIdFromJwt, Long userIdFromRequestPayload);
}
