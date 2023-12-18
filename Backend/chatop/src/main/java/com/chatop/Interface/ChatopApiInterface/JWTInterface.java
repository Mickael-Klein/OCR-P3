package com.chatop.Interface.ChatopApiInterface;

import org.springframework.security.oauth2.jwt.Jwt;

/**
 * Interface for JWT (JSON Web Token) service operations.
 */
public interface JWTInterface {
  /**
   * Generates a JWT token for the given user ID.
   *
   * @param id The user ID.
   * @return The generated JWT token.
   */
  String generateToken(long id);

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
  long getUserIdFromJwtlong(Jwt jwt);

  /**
   * Checks if the user IDs from the JWT and the request payload match.
   *
   * @param userIdFromJwt           The user ID extracted from the JWT.
   * @param userIdFromRequestPayload The user ID from the request payload.
   * @return True if the user IDs match, otherwise false.
   */
  boolean areUserIdMatching(long userIdFromJwt, long userIdFromRequestPayload);
}
