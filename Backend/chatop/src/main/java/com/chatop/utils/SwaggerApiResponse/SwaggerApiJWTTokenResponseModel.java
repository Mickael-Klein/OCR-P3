package com.chatop.utils.SwaggerApiResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SwaggerApiJWTTokenResponseModel {

  @Schema(example = "jwt")
  private String token;
}
