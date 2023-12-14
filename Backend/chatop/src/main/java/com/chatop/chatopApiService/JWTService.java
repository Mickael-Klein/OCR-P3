package com.chatop.chatopApiService;

import com.chatop.utils.Interface.ChatopApiServiceInterface.JWTServiceInterface;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

/**
 * Service for handling JSON Web Token (JWT) operations.
 */
@Service
public class JWTService implements JWTServiceInterface {

  private JwtEncoder jwtEncoder;
  private JwtDecoder jwtDecoder;

  /**
   * Constructs a new JWTService with the provided JwtEncoder and JwtDecoder.
   *
   * @param jwtEncoder The JwtEncoder used for token generation.
   * @param jwtDecoder The JwtDecoder used for token decoding.
   */
  public JWTService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
    this.jwtEncoder = jwtEncoder;
    this.jwtDecoder = jwtDecoder;
  }

  /**
   * Generates a JWT token for the given user ID.
   *
   * @param id The user ID for whom the token is generated.
   * @return The generated JWT token.
   */
  @Override
  public String generateToken(Long id) {
    String idToString = String.valueOf(id);
    Instant now = Instant.now();
    JwtClaimsSet claims = JwtClaimsSet
      .builder()
      .issuer("self")
      .issuedAt(now)
      .expiresAt(now.plus(1, ChronoUnit.DAYS))
      .subject(idToString)
      .build();
    JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
      JwsHeader.with(MacAlgorithm.HS256).build(),
      claims
    );
    return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
  }

  /**
   * Decodes the provided JWT token.
   *
   * @param token The JWT token to decode.
   * @return The decoded Jwt object.
   */
  @Override
  public Jwt decodeToken(String token) {
    return jwtDecoder.decode(token);
  }

  /**
   * Extracts the user ID from the provided JWT token.
   *
   * @param jwt The Jwt object from which to extract the user ID.
   * @return The user ID.
   */
  @Override
  public Long getUserIdFromJwtLong(Jwt jwt) {
    return Long.parseLong(jwt.getSubject());
  }

  /**
   * Compares two user IDs to check if they match.
   *
   * @param userIdFromJwt             The user ID extracted from the JWT token.
   * @param userIdFromRequestPayload  The user ID from the request payload.
   * @return True if the user IDs match, false otherwise.
   */
  @Override
  public Boolean areUserIdMatching(
    Long userIdFromJwt,
    Long userIdFromRequestPayload
  ) {
    return userIdFromJwt.equals(userIdFromRequestPayload);
  }
}
