package com.chatop.utils.SwaggerApiResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents the Message Response in the Swagger API.
 */
@Data
public class SwaggerApiMessageResponseModel {

  /**
   * The message content.
   */
  @Schema(example = "string")
  private String message;
}
