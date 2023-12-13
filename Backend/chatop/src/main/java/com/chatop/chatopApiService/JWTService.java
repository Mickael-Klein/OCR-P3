package com.chatop.chatopApiService;

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

@Service
public class JWTService {

  private JwtEncoder jwtEncoder;
  private JwtDecoder jwtDecoder;

  public JWTService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
    this.jwtEncoder = jwtEncoder;
    this.jwtDecoder = jwtDecoder;
  }

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

  public Jwt decodeToken(String token) {
    return jwtDecoder.decode(token);
  }

  public Long getUserIdFromJwtLong(Jwt jwt) {
    return Long.parseLong(jwt.getSubject());
  }

  public Boolean areUserIdMatching(
    Long userIdFromJwt,
    Long userIdFromRequestPayload
  ) {
    return userIdFromJwt == userIdFromRequestPayload;
  }
}
