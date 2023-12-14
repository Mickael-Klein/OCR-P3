package com.chatop.utils.SwaggerApiResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents the JWT Token response in the Swagger API.
 */
@Data
public class SwaggerApiJWTTokenResponseModel {

  /**
   * The JWT token.
   */
  @Schema(example = "jwt")
  private String token;
}
